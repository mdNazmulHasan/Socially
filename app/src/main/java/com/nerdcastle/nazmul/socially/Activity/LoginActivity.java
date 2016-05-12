package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nerdcastle.nazmul.socially.R;

/**
 * Created by po on 5/11/16.
 */
public class LoginActivity extends Activity {
    EditText userNameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameET= (EditText) findViewById(R.id.userIdET);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userName1", "nc");
        editor.putString("userName2", "iid");
        editor.commit();
    }
    public void login(View view){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String savedUserName1=sharedPref.getString("userName1","");
        String savedUserName2=sharedPref.getString("userName2","");
        String userNameSubmitted=userNameET.getText().toString();
        if(userNameSubmitted.equals(savedUserName1)){
            Toast.makeText(LoginActivity.this, "login as NC", Toast.LENGTH_SHORT).show();
            Intent loginIntent=new Intent(this,NewsFeedActivity.class);
            loginIntent.putExtra("userName",savedUserName1);
            startActivity(loginIntent);
        }else if(userNameSubmitted.equals(savedUserName2)){
            Toast.makeText(LoginActivity.this, "login as IID", Toast.LENGTH_SHORT).show();
            Intent loginIntent=new Intent(this,NewsFeedActivity.class);
            loginIntent.putExtra("userName",savedUserName2);
            startActivity(loginIntent);

        }else{
            Toast.makeText(LoginActivity.this, "User doesn't exist!Check again", Toast.LENGTH_SHORT).show();
        }
    }
}
