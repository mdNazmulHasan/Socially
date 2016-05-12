package com.nerdcastle.nazmul.socially;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.nerdcastle.nazmul.socially.Manager.PostTableManager;

import java.util.ArrayList;

/**
 * Created by po on 5/12/16.
 */
public class NewsFeedActivity extends Activity {
    ListView newsFeedLV;
    private ArrayList<PostModel> allPostList;
    NewsFeedAdapter newsFeedAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);
        newsFeedLV= (ListView) findViewById(R.id.newsFeedLV);
        PostTableManager postTableManager=new PostTableManager(this);
        allPostList=postTableManager.getAllPost();
        newsFeedAdapter=new NewsFeedAdapter(this,allPostList);
        newsFeedLV.setAdapter(newsFeedAdapter);
    }
    public void newPost(View view){
        Intent newPostIntent=new Intent(this,PostActivity.class);
        startActivity(newPostIntent);
    }
}
