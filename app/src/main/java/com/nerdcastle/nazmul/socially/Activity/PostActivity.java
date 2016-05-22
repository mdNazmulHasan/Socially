package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nerdcastle.nazmul.socially.Manager.PostTableManager;
import com.nerdcastle.nazmul.socially.Manager.ProfileTableManager;
import com.nerdcastle.nazmul.socially.Model.PostModel;
import com.nerdcastle.nazmul.socially.Model.ProfileModel;
import com.nerdcastle.nazmul.socially.R;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PostActivity extends Activity {

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private EditText statusET;
    private String status;
    private String photoPath;
    private String videoPath;
    String userName;
    Boolean photoCapture=true;
    PostModel postModel;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "IID";

    private Uri fileUri;

    private ImageView imgPreview;
    ProfileTableManager profileTableManager;
    ProfileModel profileModel;
    String date;
    private VideoView videoPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        userName = getIntent().getStringExtra("userName");

        imgPreview = (ImageView) findViewById(R.id.previewIV);
        videoPreview= (VideoView) findViewById(R.id.videoPreview);
        statusET = (EditText) findViewById(R.id.msgET);
        imgPreview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createDialouge();

            }
        });
        videoPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                createDialouge();
                return false;
            }
        });
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            finish();
        }
        profileTableManager = new ProfileTableManager(this);
        profileModel = profileTableManager.getProfileByUserName(userName);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        date = dateFormat.format(cal.getTime());
        //Toast.makeText(PostActivity.this, date, Toast.LENGTH_SHORT).show();
    }

    private void createDialouge() {
        new LovelyStandardDialog(PostActivity.this, R.style.TintTheme)
                .setTopColorRes(R.color.indigo)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.media)
                .setTitle("Option")
                .setMessage("If you want to post video press 'Capture Video' or 'Capture Photo' to post photo")
                .setPositiveButton("Capture Photo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        captureImage();
                    }
                })
                .setNegativeButton("Capture Video", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recordVideo();
                    }
                })
                .show();
    }

    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // name

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // video successfully recorded
                // preview the recorded video
                previewVideo();
                photoCapture=false;

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    /*
	 * Previewing recorded video
	 */
    private void previewVideo() {
        try {
            // hide image preview
            imgPreview.setVisibility(View.GONE);

            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.setVideoPath(fileUri.getPath());
            // start playing
            videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 10;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imgPreview.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
// External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void post(View view) {
        status = statusET.getText().toString().trim();
        int profileId = profileModel.getProfileId();
        photoOrVideoPost(profileId);


            if(status.length()>0|photoPath!=null|videoPath!=null) {
                PostTableManager postTableManager = new PostTableManager(this);
                postTableManager.insertPost(postModel);
                Intent postIntent = new Intent(this, ProfileActivity.class);
                postIntent.putExtra("userName", userName);
                startActivity(postIntent);

            }else {
                Toast.makeText(PostActivity.this, "There is nothing to post", Toast.LENGTH_SHORT).show();

            }




    }

    private void photoOrVideoPost(int profileId) {
        if(photoCapture){
            try{
                photoPath = fileUri.getPath();
                postModel = new PostModel(status, photoPath, profileId, date);
            }catch (Exception e){
                postModel=new PostModel(status,profileId,date);

            }

        }else {

            videoPath=fileUri.getPath();
            postModel = new PostModel(status,profileId, date,videoPath);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                Intent logoutIntent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(logoutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }

            });
        }

    }
}
