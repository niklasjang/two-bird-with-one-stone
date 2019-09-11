package com.example.twobirdwithonestone.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.twobirdwithonestone.R;

public class HomeListViewItem {
    private Drawable iconDrawable;

    private String imgUrlStr ="";
    private String titleStr = "";
    private String subtitleStr  = "";
    private String rankStr ="";
    private String urlStr ="";

    private String coinStr = "";


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public void setImgUrl(String img) { imgUrlStr = img; }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setSubtitle(String subtitle) { subtitleStr = subtitle; }
    public void setRank(String rank) { rankStr = rank; }
    public void setUrl(String url) { urlStr = url; }

    public void setCoin(String sub) { coinStr = sub ; }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }

    public String getImgUrl(){ return this.imgUrlStr ; }
    public String getTitle() { return this.titleStr ; }
    public String getSubtitle() { return this.subtitleStr ; }
    public String getRank() { return this.rankStr ; }
    public String getUrl() { return this.urlStr ; }

    public String getCoin() { return this.coinStr ; }

}
