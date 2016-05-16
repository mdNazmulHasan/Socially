package com.nerdcastle.nazmul.socially.Manager;

import android.content.Context;

import com.nerdcastle.nazmul.socially.Gateway.ConnectedProfileTableDataSource;
import com.nerdcastle.nazmul.socially.Model.ConnectedProfileModel;

import java.util.ArrayList;

/**
 * Created by po on 5/16/16.
 */
public class ConnectedProfileTableManager {
    Context context;
    ConnectedProfileTableDataSource connectedProfileTableDataSource;

    public ConnectedProfileTableManager(Context context) {
        this.context = context;
    }

    public long insertConnectedProfile(ConnectedProfileModel connectedProfileModel){
        connectedProfileTableDataSource=new ConnectedProfileTableDataSource(context);
        return connectedProfileTableDataSource.insertConnectedProfile(connectedProfileModel);
    }

    public ArrayList<ConnectedProfileModel> getAllConnectedProfileById(int profileId){
        connectedProfileTableDataSource=new ConnectedProfileTableDataSource(context);
        return connectedProfileTableDataSource.getAllConnectedProfileById(profileId);
    }
}
