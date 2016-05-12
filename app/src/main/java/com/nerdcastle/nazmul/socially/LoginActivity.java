package com.nerdcastle.nazmul.socially;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by po on 5/11/16.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void login(View view){
        Intent loginIntent=new Intent(this,NewsFeedActivity.class);
        startActivity(loginIntent);
    }
}
