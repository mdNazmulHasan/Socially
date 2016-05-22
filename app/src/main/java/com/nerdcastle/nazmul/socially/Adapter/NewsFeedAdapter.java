package com.nerdcastle.nazmul.socially.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nerdcastle.nazmul.socially.Model.PostModel;
import com.nerdcastle.nazmul.socially.R;

import java.util.ArrayList;

/**
 * Created by po on 5/11/16.
 */
public class NewsFeedAdapter extends ArrayAdapter<PostModel> {
    ArrayList<PostModel> postModelArrayList;
    private Activity context;
    private String userName;


    static class ViewHolder {
        public TextView mStatus;
        public ImageView image;
        public ImageView logoIV;
        public TextView profileNameTV;
    }
    public NewsFeedAdapter(Activity context, ArrayList<PostModel> postModelArrayList, String userName) {
        super(context, R.layout.newsfeed_row ,postModelArrayList);
        this.postModelArrayList=postModelArrayList;
        this.context = context;
        this.userName=userName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.newsfeed_row, parent,
                    false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.ivImage);
            viewHolder.logoIV = (ImageView) rowView
                    .findViewById(R.id.logoIV);
            viewHolder.mStatus= (TextView) rowView
                    .findViewById(R.id.statusTV);
            viewHolder.profileNameTV= (TextView) rowView
                    .findViewById(R.id.profileNameTV);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        int profileId=postModelArrayList.get(position).getProfileId();
        if(profileId==1){
            holder.logoIV.setImageResource(R.drawable.iid);
            holder.profileNameTV.setText("IID");
        }else if(profileId==2){
            holder.logoIV.setImageResource(R.drawable.child);
            holder.profileNameTV.setText("Save The Children");
        }else if(profileId==3){
            holder.logoIV.setImageResource(R.drawable.jaago);
            holder.profileNameTV.setText("JAAGO");
        }else if(profileId==4){
            holder.logoIV.setImageResource(R.drawable.brac);
            holder.profileNameTV.setText("BRAC");
        }else if(profileId==5){
            holder.logoIV.setImageResource(R.drawable.shakti);
            holder.profileNameTV.setText("Shakti");
        }
        final String videoPath=postModelArrayList.get(position).getVideoPath();
        holder.mStatus.setText( ""+postModelArrayList.get(position).getmStatus());

        if(videoPath != null && !videoPath.isEmpty()){
            //Toast.makeText(context, "Video is here", Toast.LENGTH_SHORT).show();
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND);

            holder.image.setImageBitmap(thumb);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context,R.style.TintTheme);
                    dialog.setContentView(R.layout.video_layout);
                    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    VideoView myVideo = (VideoView) dialog.findViewById(R.id.videoPlayer);
                    myVideo.setVideoPath(videoPath);
                    MediaController mc = new MediaController(context);
                    mc.setMediaPlayer(myVideo);
                    mc.setAnchorView(myVideo);
                    myVideo.setMediaController(mc);
                    myVideo.requestFocus();
                    myVideo.start();
                    dialog.show();
                }
            });

        }else{
            holder.image.setOnClickListener(null);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 14;
            Bitmap bitmap= BitmapFactory.decodeFile(postModelArrayList.get(position).getmPhotopath(),options);
            holder.image.setImageBitmap(bitmap);

        }
        return rowView;
    }

}
