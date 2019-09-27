package com.example.twobirdwithonestone.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.twobirdwithonestone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/*
작성자 : 박규영
수정일 : 2019 09 12
변수 수정, 수정할 버전으로 사용할 것,
* */

public class HomeListViewAdapter extends BaseAdapter {
    DataBase db;
    /**
     * Adapter가 하는 역할은 사용자 데이터를 입력받아 View를 생성하는 것이며 Adapter에서 생성되는 View는 ListView 내 하나의 아이템 영역에 표시되는 것입니다.
     * */
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
        /**
         TextView coinTextView = (TextView) convertView.findViewById(R.id.coin_text_view) ;
         ImageView iconImageView = (ImageView) convertView.findViewById(R.id.image_view) ;
         */

        ImageView imgUrlImageView = (ImageView) convertView.findViewById(R.id.imgUrl_image_view) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view) ;
        TextView contentTextView = (TextView) convertView.findViewById(R.id.content_text_view) ;
        TextView rankTextView = (TextView) convertView.findViewById(R.id.date_textview) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final HomeListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        /**
         iconImageView.setImageDrawable(listViewItem.getIcon());
         coinTextView.setText(listViewItem.getCoin());
         */
        if(listViewItem.getImgUrl() == "default"){
            Drawable image = (Drawable) ContextCompat.getDrawable(convertView.getContext(), R.drawable.i_seoul_u);
            imgUrlImageView.setImageDrawable(image);
        }else{
            Glide.with(convertView.getContext()).load(listViewItem.getImgUrl()).into(imgUrlImageView);
        }
        titleTextView.setText(listViewItem.getTitle());
        contentTextView.setText(listViewItem.getContent());
        rankTextView.setText(listViewItem.getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DataBase();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                db.registerUserData("Users",uid).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        db.updateUserPoint("Users", FirebaseAuth.getInstance().getCurrentUser().getUid(),2);
                    }
                });
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listViewItem.getUrl())));
            }
        });

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
    public void addItem(HomeListViewItem item) {
        listViewItemList.add(item);
    }

    public void clear(){
        listViewItemList.clear();
    }

}