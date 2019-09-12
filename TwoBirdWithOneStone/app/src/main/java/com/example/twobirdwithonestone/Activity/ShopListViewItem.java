package com.example.twobirdwithonestone.Activity;

import android.graphics.drawable.Drawable;

public class ShopListViewItem {
    private int iconDrawable ;
    private String titleStr ;
    private String coinStr ;

    public void setIcon(int icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setCoin(String sub) { coinStr = sub ; }


    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getCoin() { return this.coinStr ; }

}
