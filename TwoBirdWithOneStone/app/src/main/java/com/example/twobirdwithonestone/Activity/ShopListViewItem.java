package com.example.twobirdwithonestone.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ShopListViewItem {
    //shoplistview에 들어갈 이미지, 상품명, 가격 순
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
