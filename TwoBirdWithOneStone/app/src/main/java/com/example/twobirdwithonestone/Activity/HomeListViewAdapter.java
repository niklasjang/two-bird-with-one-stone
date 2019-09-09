package com.example.twobirdwithonestone.Activity;

import com.example.twobirdwithonestone.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeListViewAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<HomeListViewItem> listViewItemList = new ArrayList<HomeListViewItem>() ;

    // ListViewAdapter의 생성자
    public HomeListViewAdapter() {

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
            convertView = inflater.inflate(R.layout.home_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.image_view) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view) ;
        TextView subTitleTextView = (TextView) convertView.findViewById(R.id.subtitle_text_view) ;
        TextView coinTextView = (TextView) convertView.findViewById(R.id.coin_text_view) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        HomeListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        subTitleTextView.setText(listViewItem.getSubtitle());
        coinTextView.setText(listViewItem.getCoin());

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

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, String title, String subtitle, String coin) {
        HomeListViewItem item = new HomeListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setSubtitle(subtitle);
        item.setCoin(coin);

        listViewItemList.add(item);
    }

    public void clear(){
        listViewItemList.clear();
    }

}
