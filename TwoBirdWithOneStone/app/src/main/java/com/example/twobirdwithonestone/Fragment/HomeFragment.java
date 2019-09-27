package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.ShopGridViewAdapter;
import com.example.twobirdwithonestone.Activity.SubHomeActivity;
import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private GridView mGridView;
    private ShopGridViewAdapter mAdapter;
    private Context mContext;

    ViewFlipper v_fllipper;
    ImageView imageView;
    int imageAd = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        mGridView = (GridView)view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopGridViewAdapter();

        mGridView.setAdapter(mAdapter);
        mContext = getContext();

        int images[] = {
                R.drawable.ic_home_ad_1, R.drawable.ic_home_ad_2, R.drawable.ic_home_ad_3,
                R.drawable.ic_home_ad_4, R.drawable.ic_home_ad_5, R.drawable.ic_home_ad_6};

        v_fllipper = view.findViewById(R.id.imageAdSlide);

        for(int image : images) {
            fllipperImages(image);
        }

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "traffic");
                    startActivity(intent);
                }
                if (i == 1) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "house");
                    startActivity(intent);
                }
                if (i == 2) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "welfare");
                    startActivity(intent);
                }
                if (i == 3) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "news");
                    startActivity(intent);
                }
                if (i == 4) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "festival");
                    startActivity(intent);
                }
                if (i == 5) {
                    Intent intent = new Intent(mContext, SubHomeActivity.class);
                    intent.putExtra("category", "event");
                    startActivity(intent);
                }
            }
        });

        ArrayList<String> listTitle = new ArrayList<String>();
        listTitle.add("교통"); listTitle.add("주택"); listTitle.add("복지");
        listTitle.add("소식"); listTitle.add("행사 및 축제"); listTitle.add("이벤트 신청");

        ArrayList<Drawable> listIcon = new ArrayList<Drawable>();

        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_transportation));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_housing));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_welfare));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_administrative));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_culture));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_home_safety));

        //gridview에 gridview item 루프돌며 생성
        for(int i=0; i<listTitle.size();i++){
            mAdapter.addItem(listIcon.get(i),listTitle.get(i));
        }

        return view;
    }

    public void fllipperImages(int image) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(image);

        v_fllipper.addView(imageView);      // 이미지 추가
        v_fllipper.setFlipInterval(10000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper.setInAnimation(mContext,android.R.anim.slide_in_left);
        v_fllipper.setOutAnimation(mContext,android.R.anim.slide_out_right);
    }
}


