package com.nerdcastle.nazmul.socially.Gateway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nerdcastle.nazmul.socially.Model.PostModel;

import java.util.ArrayList;

/**
 * Created by po on 5/12/16.
 */
public class PostTableDataSource {

    private DatabaseHelperClass dbHelper;

    public PostTableDataSource(Context context) {
        dbHelper = new DatabaseHelperClass(context);
    }


    public long insertPost(PostModel postModel) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", postModel.getmStatus());
        contentValues.put("photo", postModel.getmPhotopath());
        contentValues.put("profileId", postModel.getProfileId());
        contentValues.put("date", postModel.getDate());

        return db.insert(DatabaseHelperClass.POST_TABLE_NAME, null,
                contentValues);

    }

    public ArrayList<PostModel> getAllPost() {

        ArrayList<PostModel> allPostList = new ArrayList<PostModel>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from post order by postId desc", null);
        if (result.moveToFirst()) {
            do {

                int postId = result.getInt(0);
                String status = result.getString(1);
                String photo = result.getString(2);
                int profileId = result.getInt(3);
                String date = result.getString(4);

                PostModel postModel=new PostModel(postId,status,photo,profileId,date);
                allPostList.add(postModel);
            } while (result.moveToNext());

        }
        return allPostList;
    }

    public ArrayList<PostModel> getAllPostOfConnectedProfile(int profileId) {

        ArrayList<PostModel> allConnectedPostList = new ArrayList<PostModel>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from post where profileId In (select connectedProfileId from profileConnection where profileId ="+profileId+") or profileId ="+profileId +" order by postId desc", null);
        if (result.moveToFirst()) {
            do {

                int postId = result.getInt(0);
                String status = result.getString(1);
                String photo = result.getString(2);
                profileId = result.getInt(3);
                String date = result.getString(4);

                PostModel postModel=new PostModel(postId,status,photo,profileId,date);
                allConnectedPostList.add(postModel);
            } while (result.moveToNext());

        }
        return allConnectedPostList;
    }
    public ArrayList<PostModel> getAllPostByUser(int profileId) {

        ArrayList<PostModel> allPostListByUser = new ArrayList<PostModel>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from (select * from post order by postId desc) where profileId ="+profileId, null);
        if (result.moveToFirst()) {
            do {

                int postId = result.getInt(0);
                String status = result.getString(1);
                String photo = result.getString(2);
                profileId = result.getInt(3);
                String date = result.getString(4);

                PostModel postModel=new PostModel(postId,status,photo,profileId,date);
                allPostListByUser.add(postModel);
            } while (result.moveToNext());

        }
        return allPostListByUser;
    }

}
