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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listshop);
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new ShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //shop_fragment에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();
        //ArrayList 를 받아 addItem
        final ArrayList<Items> list = intent.getParcelableArrayListExtra("culture_list");

        for(int i=0;i<list.size();i++){
            mAdapter.addItem(BitmapFactory.decodeByteArray(list.get(i).image, 0, list.get(i).image.length),list.get(i).name,list.get(i).price);
            }
        //


        //Shoplistview ->> subshoplistview
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if ( i == 0) {


                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                    ArrayList<Items> coffee_list = new ArrayList<Items>();
                    coffee_list.add(new Items(list.get(i).image,list.get(i).name,list.get(i).price));
                    intent.putParcelableArrayListExtra("coffee_list", coffee_list);
                    startActivity(intent);
                }
                if ( i == 1) {
                    Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
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
