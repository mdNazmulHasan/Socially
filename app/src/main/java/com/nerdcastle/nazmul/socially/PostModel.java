package com.nerdcastle.nazmul.socially;

/**
 * Created by po on 5/11/16.
 */
public class PostModel {
    public int mId;
    public String mStatus;
    public String mPhotopath;

    public PostModel(int mId, String mStatus, String mPhotopath) {
        this.mId = mId;
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
    }

    public PostModel(String mStatus, String mPhotopath) {
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
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
