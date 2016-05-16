package com.nerdcastle.nazmul.socially.Manager;

import android.content.Context;

import com.nerdcastle.nazmul.socially.Model.PostModel;
import com.nerdcastle.nazmul.socially.Gateway.PostTableDataSource;

import java.util.ArrayList;

/**
 * Created by po on 5/12/16.
 */
public class PostTableManager {
    Context context;
    PostTableDataSource postTableDataSource;

    public PostTableManager(Context context) {
        this.context = context;
    }

    public long insertPost(PostModel postModel){
        postTableDataSource=new PostTableDataSource(context);
        return postTableDataSource.insertPost(postModel);
    }
    public ArrayList<PostModel> getAllPost(){
        postTableDataSource=new PostTableDataSource(context);
        return postTableDataSource.getAllPost();
    }
    public ArrayList<PostModel> getAllPostByUser(String userName){
        postTableDataSource=new PostTableDataSource(context);
        return postTableDataSource.getAllPostByUser(userName);
    }
    public ArrayList<PostModel> getAllPostOfConnectedProfile(int profileId) {
        postTableDataSource=new PostTableDataSource(context);
        return postTableDataSource.getAllPostOfConnectedProfile(profileId);
    }
}
