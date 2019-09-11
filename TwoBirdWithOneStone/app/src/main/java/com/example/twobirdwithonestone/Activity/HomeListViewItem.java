package com.example.twobirdwithonestone.Activity;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

import com.example.twobirdwithonestone.R;


public class HomeListViewItem {
    private Drawable iconDrawable;
    private String titleStr = "";
    private String coinStr = "";
    private String subtitleStr  = "";

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setSubtitle(String subtitle) { subtitleStr = subtitle; }
    public void setCoin(String sub) { coinStr = sub ; }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getSubtitle() { return this.subtitleStr ; }
    public String getCoin() { return this.coinStr ; }
}
