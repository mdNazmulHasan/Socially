package com.nerdcastle.nazmul.socially.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.nerdcastle.nazmul.socially.Manager.PostTableManager;
import com.nerdcastle.nazmul.socially.Adapter.NewsFeedAdapter;
import com.nerdcastle.nazmul.socially.Model.PostModel;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);
        userName=getIntent().getStringExtra("userName");
        userName=getIntent().getStringExtra("userName");
        newsFeedLV= (ListView) findViewById(R.id.newsFeedLV);
        PostTableManager postTableManager=new PostTableManager(this);
        allPostList=postTableManager.getAllPostByUser(userName);
        newsFeedAdapter=new NewsFeedAdapter(this,allPostList);
        newsFeedLV.setAdapter(newsFeedAdapter);
    }
    public void newPost(View view){
        Intent newPostIntent=new Intent(this,PostActivity.class);
        newPostIntent.putExtra("userName",userName);
        startActivity(newPostIntent);
    }
}
