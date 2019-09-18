package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class ShopListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private ShopListViewAdapter mAdapter;
    @Override
    //listshop과 연결
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listshop);
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new ShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //shop_fragment에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();
        //parcelable로 ArrayList 를 받아 addItem
        final ArrayList<Items> list = intent.getParcelableArrayListExtra("culture_list");

        for(int i=0;i<list.size();i++){
            mAdapter.addItem(BitmapFactory.decodeByteArray(list.get(i).image, 0, list.get(i).image.length),list.get(i).name,list.get(i).price);
            }
        //


        //Shoplistview ->> subshoplistview 클릭하는 position마다 조건달아 지정
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if ( i == 0) {

                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    ArrayList<Items> coffee_list = new ArrayList<Items>();
                    //받아온 arraylist의 items 객체 내 가져올 항목 add
                    coffee_list.add(new Items(list.get(i).image,list.get(i).category,list.get(i).name,list.get(i).price,list.get(i).explanation));
                    //parcelable로 arraylist 넘기기
                    intent.putParcelableArrayListExtra("coffee_list", coffee_list);
                    startActivity(intent);
                }
                if ( i == 1) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    ArrayList<Items> coffee_list = new ArrayList<Items>();
                    coffee_list.add(new Items(list.get(i).image,list.get(i).category,list.get(i).name,list.get(i).price,list.get(i).explanation));
                    intent.putParcelableArrayListExtra("coffee_list", coffee_list);
                    startActivity(intent);
                }
                if ( i == 2) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    startActivity(intent);
                }
                if ( i == 3) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    startActivity(intent);
                }
                if ( i == 4) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    startActivity(intent);
                }
                if ( i == 5) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    startActivity(intent);
                }
            }

        });


    }
    }
