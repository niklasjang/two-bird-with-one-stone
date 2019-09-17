package com.example.twobirdwithonestone.Activity;

import android.graphics.Bitmap;

public class SubShopListViewItem {
    //subshoplistview에 들어갈 category명, 상품명, 이미지, 상품설명
    private String categoryStr ;
    private String titleStr ;
    private Bitmap imageByte ;
    private String explanationStr ;

    public void setCategory(String sub) { categoryStr = sub ; }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setIcon(Bitmap image) {
        imageByte = image ;
    }
    public void setExplanation(String explanation) { explanationStr = explanation ; }

    public String getCategory() { return this.categoryStr ; }
    public String getTitle() {
        return this.titleStr ;
    }
    public Bitmap getIcon() {
        return this.imageByte ;
    }
    public String getExplanation() { return this.explanationStr ; }
}
