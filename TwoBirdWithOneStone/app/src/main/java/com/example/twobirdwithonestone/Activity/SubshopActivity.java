package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.twobirdwithonestone.R;

import java.io.Serializable;
import java.util.ArrayList;

public class SubshopActivity extends AppCompatActivity {
    private ListView mListView;
    private ShopListViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subshop);
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new ShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //shop_fragment에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();

        ArrayList<Items> list = intent.getParcelableArrayListExtra("coffee_list");
        for(int i=0;i<list.size();i++){
            mAdapter.addItem(list.get(i).icon,list.get(i).name,list.get(i).price);
        }


        }


    }
