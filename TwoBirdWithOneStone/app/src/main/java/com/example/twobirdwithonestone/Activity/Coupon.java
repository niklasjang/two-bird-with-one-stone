package com.example.twobirdwithonestone.Activity;

public class Coupon {
    public String couponName;
    public String couponCreateTime;
    public Boolean couponUesrOrNot;
    public Coupon( String _name, String _time, Boolean _userOrNot){
        this.couponName = _name;
        this.couponCreateTime = _time;
        this.couponUesrOrNot = _userOrNot;
    }
}
