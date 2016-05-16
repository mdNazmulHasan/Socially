package com.nerdcastle.nazmul.socially.Manager;

import android.content.Context;

import com.nerdcastle.nazmul.socially.Gateway.ProfileTableDataSource;
import com.nerdcastle.nazmul.socially.Model.ProfileModel;

/**
 * Created by po on 5/16/16.
 */
public class ProfileTableManager {
    Context context;
    ProfileTableDataSource profileTableDataSource;

    public ProfileTableManager(Context context) {
        this.context = context;
    }

    public long insertProfile(ProfileModel profileModel){
        profileTableDataSource=new ProfileTableDataSource(context);
        return profileTableDataSource.insertProfile(profileModel);
    }

    public ProfileModel getProfileByUserName(String userName) {
        profileTableDataSource=new ProfileTableDataSource(context);
        return profileTableDataSource.getProfileByUserName(userName);
    }
}
