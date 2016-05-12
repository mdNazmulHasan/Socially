package com.nerdcastle.nazmul.socially.Manager;

import android.content.Context;

import com.nerdcastle.nazmul.socially.PostModel;
import com.nerdcastle.nazmul.socially.PostTableDataSource;

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
}
