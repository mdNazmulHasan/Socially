package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.nerdcastle.nazmul.socially.Adapter.NewsFeedAdapter;
import com.nerdcastle.nazmul.socially.Manager.PostTableManager;
import com.nerdcastle.nazmul.socially.Manager.ProfileTableManager;
import com.nerdcastle.nazmul.socially.Model.PostModel;
import com.nerdcastle.nazmul.socially.Model.ProfileModel;
import com.nerdcastle.nazmul.socially.R;

import java.util.ArrayList;

/**
 * Created by po on 5/12/16.
 */
public class NewsFeedActivity extends Activity {
    ListView newsFeedLV;
    private ArrayList<PostModel> allPostList;
    NewsFeedAdapter newsFeedAdapter;
    String userName;
    ProfileModel profileModel;
    ProfileTableManager profileTableManager;
    int profileId;
    ImageView logoImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);
        viewInitialization();
        userName=getIntent().getStringExtra("userName");
        profileTableManager=new ProfileTableManager(this);
        profileModel=profileTableManager.getProfileByUserName(userName);
        profileId=profileModel.getProfileId();

        PostTableManager postTableManager=new PostTableManager(this);
        allPostList=postTableManager.getAllPostOfConnectedProfile(profileId);
        newsFeedAdapter=new NewsFeedAdapter(this,allPostList,userName);
        newsFeedLV.setAdapter(newsFeedAdapter);
        logoChange();
    }

    private void logoChange() {
        if(profileId==1){
            logoImageView.setImageResource(R.drawable.iid);
        }else if(profileId==2){
            logoImageView.setImageResource(R.drawable.child);
        }else if(profileId==3){
            logoImageView.setImageResource(R.drawable.jaago);
        }else if(profileId==4){
            logoImageView.setImageResource(R.drawable.brac);
        }else if(profileId==5){
            logoImageView.setImageResource(R.drawable.shakti);
        }
    }

    private void viewInitialization() {
        logoImageView= (ImageView) findViewById(R.id.logoIV);
        newsFeedLV= (ListView) findViewById(R.id.newsFeedLV);
    }

    public void newPost(View view){
        Intent newPostIntent=new Intent(this,PostActivity.class);
        newPostIntent.putExtra("userName",userName);
        startActivity(newPostIntent);
    }

    public void logout(View view){
        Intent logoutIntent = new Intent(getApplicationContext(),
                LoginActivity.class);
        startActivity(logoutIntent);
    }
    public void profile(View view){
        Intent profileIntent=new Intent(this,ProfileActivity.class);
        profileIntent.putExtra("userName",userName);
        startActivity(profileIntent);
    }
}
