package com.example.twobirdwithonestone.Activity;

//user collection의 데이터
public class UserData {
    private String uid;
    private long point;
    private boolean switchLockScreen;

    public UserData() {
        //no argu
    }

    public UserData(String uid,long point, boolean switchLockScreen) {
        this.uid = uid;
        this.point = point;
        this.switchLockScreen = switchLockScreen;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public boolean isSwitchLockScreen() {
        return switchLockScreen;
    }

    public void setSwitchLockScreen(boolean switchLockScreen) {
        this.switchLockScreen = switchLockScreen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
