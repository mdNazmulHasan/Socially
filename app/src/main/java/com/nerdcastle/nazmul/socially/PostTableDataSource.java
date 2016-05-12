package com.nerdcastle.nazmul.socially;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by po on 5/12/16.
 */
public class PostTableDataSource {

    private DatabaseHelperClass dbHelper;

    public PostTableDataSource(Context context) {
        dbHelper = new DatabaseHelperClass(context);
    }

    // Create diet into table
    public long insertPost(PostModel postModel) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", postModel.getmStatus());
        contentValues.put("photo", postModel.getmPhotopath());

        return db.insert(DatabaseHelperClass.POST_TABLE_NAME, null,
                contentValues);

    }

    public ArrayList<PostModel> getAllPost() {

        ArrayList<PostModel> allPostList = new ArrayList<PostModel>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from post order by id desc", null);
        if (result.moveToFirst()) {
            do {

                int mId = result.getInt(0);
                String status = result.getString(1);
                String photo = result.getString(2);


                PostModel postModel = new PostModel(mId,
                        status, photo);
                allPostList.add(postModel);
            } while (result.moveToNext());

        }
        return allPostList;
    }

}
