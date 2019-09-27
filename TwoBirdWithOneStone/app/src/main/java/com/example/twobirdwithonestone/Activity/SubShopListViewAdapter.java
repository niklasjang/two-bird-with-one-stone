package com.example.twobirdwithonestone.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class SubShopListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<SubShopListViewItem> listViewItemList = new ArrayList<SubShopListViewItem>() ;

    // ListViewAdapter의 생성자
    public SubShopListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.shop_sublistview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        //category, title, image, explain 순
        TextView categoryTextView = (TextView) convertView.findViewById(R.id.category_text_view) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_view) ;
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.image_view) ;
        TextView explanationTextView = (TextView) convertView.findViewById(R.id.explanation_text_view) ;



        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        SubShopListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영

        categoryTextView.setText(listViewItem.getCategory());
        titleTextView.setText(listViewItem.getTitle());
        iconImageView.setImageBitmap(listViewItem.getIcon());
        explanationTextView.setText(listViewItem.getExplanation());
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    //이미지, 카테고리 제목, 가격, 설명
    public void addItem(String category, String title, Bitmap image,String _priceStr, String explanation) {
        SubShopListViewItem item = new SubShopListViewItem();

        item.setCategory(category);
        item.setTitle(title);
        item.setIcon(image);
        item.setPriceStr(_priceStr);
        item.setExplanation(explanation);
        listViewItemList.add(item);
    }

    public void clear(){
        listViewItemList.clear();
    }

}
