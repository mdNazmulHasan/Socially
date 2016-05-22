package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nerdcastle.nazmul.socially.Manager.ConnectedProfileTableManager;
import com.nerdcastle.nazmul.socially.Manager.ProfileTableManager;
import com.nerdcastle.nazmul.socially.Model.ConnectedProfileModel;
import com.nerdcastle.nazmul.socially.Model.ProfileModel;
import com.nerdcastle.nazmul.socially.R;

import java.util.ArrayList;

/**
 * Created by po on 5/11/16.
 */
public class LoginActivity extends Activity {
    EditText userNameET;
    String userName;
    private ArrayList<ConnectedProfileModel> allConnectedProfileList;
    ProfileTableManager profileTableManager;
    Button dummyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameET = (EditText) findViewById(R.id.userIdET);
        dummyBtn= (Button) findViewById(R.id.dummyBtn);

        profileTableManager=new ProfileTableManager(this);
        ProfileModel profileModel=profileTableManager.getAllProfile();
        if(profileModel != null){
            dummyBtn.setVisibility(View.GONE);
        }


    }

    public void login(View view) {
        userName = userNameET.getText().toString();
        ProfileTableManager profileTableManager = new ProfileTableManager(this);
        ProfileModel profileModel = profileTableManager.getProfileByUserName(userName);
        if (profileModel == null) {
            Toast.makeText(LoginActivity.this, "user doesn't exist, try again!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Logged in as " + profileModel.getName().toString(), Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(this, NewsFeedActivity.class);
            loginIntent.putExtra("userName", userName);
            startActivity(loginIntent);
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

    public void dummy(View view){
        profileTableManager=new ProfileTableManager(this);
        ProfileModel p1=new ProfileModel("IID","iid");
        ProfileModel p2=new ProfileModel("Save The Children","child");
        ProfileModel p3=new ProfileModel("JAAGO","jaago");
        ProfileModel p4=new ProfileModel("BRAC","brac");
        ProfileModel p5=new ProfileModel("Shakti","shakti");
        profileTableManager.insertProfile(p1);
        profileTableManager.insertProfile(p2);
        profileTableManager.insertProfile(p3);
        profileTableManager.insertProfile(p4);
        profileTableManager.insertProfile(p5);

        ConnectedProfileTableManager connectedProfileTableManager=new ConnectedProfileTableManager(this);
        ConnectedProfileModel c1=new ConnectedProfileModel(1,2);
        ConnectedProfileModel c2=new ConnectedProfileModel(1,3);
        ConnectedProfileModel c3=new ConnectedProfileModel(2,1);
        ConnectedProfileModel c4=new ConnectedProfileModel(2,3);
        ConnectedProfileModel c5=new ConnectedProfileModel(3,2);
        ConnectedProfileModel c6=new ConnectedProfileModel(3,1);
        connectedProfileTableManager.insertConnectedProfile(c1);
        connectedProfileTableManager.insertConnectedProfile(c2);
        connectedProfileTableManager.insertConnectedProfile(c3);
        connectedProfileTableManager.insertConnectedProfile(c4);
        connectedProfileTableManager.insertConnectedProfile(c5);
        connectedProfileTableManager.insertConnectedProfile(c6);
    }


}
