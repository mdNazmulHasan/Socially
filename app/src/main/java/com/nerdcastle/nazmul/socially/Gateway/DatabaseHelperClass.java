package com.nerdcastle.nazmul.socially.Gateway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by po on 5/12/16.
 */
public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "IID.db";

    public static final String POST_TABLE_NAME = "post";
    public static final String POST_COLUMN_ID = "postId";
    public static final String POST_COLUMN_STATUS = "status";
    public static final String POST_COLUMN_PHOTO = "photo";
    public static final String POST_COLUMN_PROFILE_ID = "profileId";
    public static final String POST_COLUMN_DATE = "date";
    public static final String POST_COLUMN_VIDEO = "video";

    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_COLUMN_ID = "profileId";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_USER_NAME = "userName";


    public static final String PROFILE_CONNECTION_TABLE_NAME = "profileConnection";
    public static final String PROFILE_CONNECTION_COLUMN_PROFILE_ID = "profileId";
    public static final String PROFILE_CONNECTION_COLUMN_CONNECTED_PROFILE_ID = "connectedProfileId";


    public DatabaseHelperClass(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("create table post"
                + "(postId integer primary key,status text,photo text,profileId integer,date text,video text)");

        db.execSQL("create table profile"
                + "(profileId integer primary key,name text,userName text)");

        db.execSQL("create table profileConnection"
                + "(profileId integer,connectedProfileId integer)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS post");
        /*db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("DROP TABLE IF EXISTS profileConnection");*/
        onCreate(db);
    }

}