package com.example.twobirdwithonestone.Activity;

import android.graphics.Bitmap;

public class SubShopListViewItem {
    private Bitmap imageByte ;
    private String titleStr ;
    private String coinStr ;
    private String explanationStr ;

    public void setIcon(Bitmap image) {
        imageByte = image ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setCoin(String sub) { coinStr = sub ; }
    public void setExplanation(String explanation) { explanationStr = explanation ; }


    public Bitmap getIcon() {
        return this.imageByte ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getCoin() { return this.coinStr ; }
    public String getExplanation() { return this.explanationStr ; }
}
