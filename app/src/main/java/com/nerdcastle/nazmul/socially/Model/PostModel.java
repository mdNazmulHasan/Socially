package com.nerdcastle.nazmul.socially.Model;

/**
 * Created by po on 5/11/16.
 */
public class PostModel {
    private int postId;
    private String mStatus;
    private String mPhotopath;
    private int profileId;
    private String date;
    private String videoPath;

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public PostModel(String mStatus, String mPhotopath, int profileId, String date, String videoPath) {
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.profileId = profileId;
        this.date = date;
        this.videoPath = videoPath;
    }

    public PostModel(int postId, String mStatus, String mPhotopath, int profileId, String date, String videoPath) {
        this.postId = postId;
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.profileId = profileId;
        this.date = date;
        this.videoPath = videoPath;
    }

    public PostModel(String mStatus, int profileId, String date, String videoPath) {
        this.mStatus = mStatus;
        this.profileId = profileId;
        this.date = date;
        this.videoPath = videoPath;
    }

    public PostModel(int postId, String mStatus, String mPhotopath, int profileId, String date) {
        this.postId = postId;
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.profileId = profileId;
        this.date = date;
    }

    public PostModel(String mStatus, String mPhotopath, int profileId, String date) {
        this.mStatus = mStatus;
        this.mPhotopath = mPhotopath;
        this.profileId = profileId;
        this.date = date;
    }

    public PostModel() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
