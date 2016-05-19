package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameET = (EditText) findViewById(R.id.userIdET);

        //hideKeyBoard();

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


}
