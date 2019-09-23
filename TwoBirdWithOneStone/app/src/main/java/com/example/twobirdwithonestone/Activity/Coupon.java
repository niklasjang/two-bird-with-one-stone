package com.example.twobirdwithonestone.Activity;

import android.graphics.Bitmap;

public class Coupon {
    public String  couponCreateTime;
    public String  couponName;
    public String  couponUID;
    public Boolean couponUesrOrNot;
    public String  userUID;
    public int     couponImgIndex;

    public Coupon(String _time, String _cName, String _cUID, Boolean _cOrNot, String _uUID, int _imgIndex){
        this.couponCreateTime = _time;
        this.couponName = _cName;
        this.couponUID = _cUID;
        this.couponUesrOrNot = _cOrNot;
        this.userUID = _uUID;
        this.couponImgIndex = _imgIndex;
    }



    public String getUserUID() {
        return userUID;
    }
}
