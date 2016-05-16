package com.nerdcastle.nazmul.socially.Gateway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nerdcastle.nazmul.socially.Model.ProfileModel;

/**
 * Created by po on 5/16/16.
 */
public class ProfileTableDataSource {

    private DatabaseHelperClass dbHelper;

    public ProfileTableDataSource(Context context) {
        dbHelper = new DatabaseHelperClass(context);
    }


    public long insertProfile(ProfileModel profileModel) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", profileModel.getName());
        contentValues.put("userName", profileModel.getUserName());

        return db.insert(DatabaseHelperClass.PROFILE_TABLE_NAME, null,
                contentValues);

    }

    public ProfileModel getProfileByUserName(String userName) {
        ProfileModel profileModel = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from profile where username ='"+userName+ "'", null);
        if (result.moveToFirst()) {
            do {
                int profileId = result.getInt(0);
                String profleName = result.getString(1);
                String profileUserName = result.getString(2);

                profileModel = new ProfileModel(profileId,
                        profleName, profileUserName);
            } while (result.moveToNext());
        }
        return profileModel;
    }

}
