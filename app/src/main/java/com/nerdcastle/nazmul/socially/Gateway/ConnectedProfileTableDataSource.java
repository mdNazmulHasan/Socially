package com.nerdcastle.nazmul.socially.Gateway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nerdcastle.nazmul.socially.Model.ConnectedProfileModel;

import java.util.ArrayList;

/**
 * Created by po on 5/16/16.
 */
public class ConnectedProfileTableDataSource {
    private DatabaseHelperClass dbHelper;

    public ConnectedProfileTableDataSource(Context context) {
        dbHelper = new DatabaseHelperClass(context);
    }

    // Create diet into table
    public long insertConnectedProfile(ConnectedProfileModel connectedProfileModel) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("profileId", connectedProfileModel.getProfleId());
        contentValues.put("connectedProfileId", connectedProfileModel.getConnectedProfleId());

        return db.insert(DatabaseHelperClass.PROFILE_CONNECTION_TABLE_NAME, null,
                contentValues);

    }

    public ArrayList<ConnectedProfileModel> getAllConnectedProfileById(int profileId) {

        ArrayList<ConnectedProfileModel> allConnectedProfileListById = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from profileConnection where profileId ="+profileId, null);
        if (result.moveToFirst()) {
            do {

                int connectedProfileId = result.getInt(1);

                ConnectedProfileModel connectedProfileModel=new ConnectedProfileModel(connectedProfileId);
                allConnectedProfileListById.add(connectedProfileModel);
            } while (result.moveToNext());

        }
        return allConnectedProfileListById;
    }
}
