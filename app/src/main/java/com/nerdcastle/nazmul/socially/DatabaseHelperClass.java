package com.nerdcastle.nazmul.socially;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by po on 5/12/16.
 */
public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "IID.db";


    /** MEDICAL HISTORY TABLE */

    public static final String POST_TABLE_NAME = "post";
    public static final String POST_COLUMN_ID = "id";
    public static final String POST_COLUMN_STATUS = "status";
    public static final String POST_COLUMN_PHOTO = "photo";

    public DatabaseHelperClass(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("create table post"
                + "(id integer primary key,status text,photo text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS diet");
        onCreate(db);
    }

}