package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class SubShopListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private SubShopListViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sublistshop);
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new SubShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //ShopListViewActivity에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();
        //ArrayList 를 받아 addItem
        ArrayList<Items> list = intent.getParcelableArrayListExtra("coffee_list");
        for(int i=0;i<list.size();i++){
            Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(i).image, 0, list.get(i).image.length);
            mAdapter.addItem(list.get(i).category,list.get(i).name,bitmap,list.get(i).explanation);
        }
        //
        Button btn_buy_item = findViewById(R.id.btn_buy_item);
        btn_buy_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
