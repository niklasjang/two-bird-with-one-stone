package com.example.twobirdwithonestone.Activity;

import android.graphics.Bitmap;

public class SubShopListViewItem {
    private Bitmap imageByte ;
    private String titleStr ;
    private String coinStr ;

    public void setIcon(Bitmap image) {
        imageByte = image ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setCoin(String sub) { coinStr = sub ; }


    public Bitmap getIcon() {
        return this.imageByte ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getCoin() { return this.coinStr ; }

}
