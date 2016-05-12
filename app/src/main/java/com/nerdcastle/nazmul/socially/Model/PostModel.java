package com.nerdcastle.nazmul.socially.Model;

/**
 * Created by po on 5/11/16.
 */
public class PostModel {
    public int mId;
    public String mStatus;
    public String mPhotopath;
    public String userName;

    public PostModel(int mId, String mStatus, String mPhotopath, String userName) {
        this.mId = mId;
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.userName = userName;
    }

    public PostModel(String mStatus, String mPhotopath, String userName) {
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.userName = userName;
    }

    public PostModel(int mId, String mStatus, String mPhotopath) {
        this.mId = mId;
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
    }

    public PostModel(String mStatus, String mPhotopath) {
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmPhotopath() {
        return mPhotopath;
    }

    public void setmPhotopath(String mPhotopath) {
        this.mPhotopath = mPhotopath;
    }
}
