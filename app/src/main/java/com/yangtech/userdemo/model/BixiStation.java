package com.yangtech.userdemo.model;

/**
 * Created by apple on 16-02-26.
 */
public class BixiStation {

    private String mStationName;
    private double mLat;
    private double mLon;
    private int mAvailableBikeNum;

    public BixiStation() {

    }

    public String getStationName() {
        return mStationName;
    }

    public void setStationName(String stationName) {
        mStationName = stationName;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLon() {
        return mLon;
    }

    public void setLon(double lon) {
        mLon = lon;
    }

    public int getAvailableBikeNum() {
        return mAvailableBikeNum;
    }

    public void setAvailableBikeNum(int availableBikeNum) {
        mAvailableBikeNum = availableBikeNum;
    }


}
