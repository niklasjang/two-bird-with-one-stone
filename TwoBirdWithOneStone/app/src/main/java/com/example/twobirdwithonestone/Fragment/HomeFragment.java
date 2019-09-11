package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.HomeListViewAdapter;
import com.example.twobirdwithonestone.R;

public class HomeFragment extends Fragment {
    private ListView mListView;
    private HomeListViewAdapter mAdapter;

    //private Drawable image = null;

    private String imgUrl = null;
    private String title = null;
    private String subtitle = null;
    private String rank = null;
    private String url = null;

    //private String coin = null;
    private String getId = null;

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        mListView = (ListView)view.findViewById(R.id.listView);
        mAdapter = new HomeListViewAdapter();
        mListView.setAdapter(mAdapter);

        /**
         * listView.setAdapter(adapter);
         *
         * adapter 부분이 에러...
         * https://recipes4dev.tistory.com/63?category=643521 참고해서 수정하기
         */

        mContext = getActivity();

        imgUrl = "이미지 Url";
        title = "제목";
        subtitle = "부제";
        rank = "순위";
        url = "Url";

        //coin = "1000";
        //image = (Drawable) ContextCompat.getDrawable(mContext, R.drawable.image_logo_bird);

        mAdapter.addItem(imgUrl, title, subtitle, rank, url);


        /**
         * 1. 리스트에 값을 넣을경우
         *
         * - title, subtitle, coin (String)형식
         *  >> title = 가져온title.toString();
         * - image(Drawable)형식
         *  >> image = (Drawable)ContextCompat.getDrawable(mContext, 이미지 소스);
         *  이미지 소스 ex)R.drawable.launcher 등등
         *
         * - 아답터에 연결
         *  >> adater.additem(Drawable icon, String title, String subtitle, String coin);
         *
         *
         * 2. 리스트 값을 전부 삭제할 경우
         *  >> adapter.notifyDataSetChanged();
         */

        return view;
    }
}
