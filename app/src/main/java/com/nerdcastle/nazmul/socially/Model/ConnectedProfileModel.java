package com.nerdcastle.nazmul.socially.Model;

/**
 * Created by po on 5/16/16.
 */
public class ConnectedProfileModel {
    private int profleId;
    private int connectedProfleId;

    public ConnectedProfileModel(int profleId, int connectedProfleId) {
        this.profleId = profleId;
        this.connectedProfleId = connectedProfleId;
    }

    public int getProfleId() {
        return profleId;
    }

    public ConnectedProfileModel(int connectedProfleId) {
        this.connectedProfleId = connectedProfleId;
    }

    public void setProfleId(int profleId) {
        this.profleId = profleId;
    }

    public int getConnectedProfleId() {
        return connectedProfleId;
    }

    public void setConnectedProfleId(int connectedProfleId) {
        this.connectedProfleId = connectedProfleId;
    }
}
