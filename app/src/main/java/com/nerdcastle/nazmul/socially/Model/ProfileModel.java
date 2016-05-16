package com.nerdcastle.nazmul.socially.Model;

/**
 * Created by po on 5/16/16.
 */
public class ProfileModel {
    private int profileId;
    private String name;
    private String userName;

    public ProfileModel(int profileId, String name, String userName) {
        this.profileId = profileId;
        this.name = name;
        this.userName = userName;
    }

    public ProfileModel(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public ProfileModel() {
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
