package com.example.twobirdwithonestone.Activity;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

import com.example.twobirdwithonestone.R;
/*
작성자 : 박규영
수정일 : 2019 09 12
변수이름 수정, 수정한 변수이름으로 설정할 것
* */
public class HomeListViewItem {
    private Drawable iconDrawable;

    private String imgUrlStr ="";
    private String titleStr = "";
    private String contentStr  = "";
    private String dateStr ="";
    private String urlStr ="";
    private String coinStr = "";


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public void setImgUrl(String img) { imgUrlStr = img; }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setContent(String content) { contentStr = content; }
    public void setDate(String rank) { dateStr = rank; }
    public void setUrl(String url) { urlStr = url; }

    public void setCoin(String sub) { coinStr = sub ; }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }

    public String getImgUrl(){ return this.imgUrlStr ; }
    public String getTitle() { return this.titleStr ; }
    public String getContent() { return this.contentStr ; }
    public String getDate() { return this.dateStr ; }
    public String getUrl() { return this.urlStr ; }
    public String getCoin() { return this.coinStr ; }

}
