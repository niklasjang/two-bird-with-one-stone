package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.twobirdwithonestone.Activity.HomeListViewAdapter;
import com.example.twobirdwithonestone.Activity.HomeListViewItem;
import com.example.twobirdwithonestone.Activity.LoginActivity;
import com.example.twobirdwithonestone.Activity.MainActivity;
import com.example.twobirdwithonestone.Activity.SubHomeActivity;
import com.example.twobirdwithonestone.R;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private ListView mListView;
    private HomeListViewAdapter mAdapter;

    private Drawable image = null;
    private String title = null;
    private String subtitle = null;
    private String coin = null;
    private String getId = null;
    // ?fetchStart=1로 페이지 라우팅을 실행
    private String seoul_url = "https://www.seoul.go.kr/realmnews/in/list.do";

    private Context mContext;
    private Button btnEnvironment;
    private Button btnEvent;
    private Button btnFestival;
    private Button btnTraffic;
    private Button btnWelfare;
    private Button btnHouse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);



        mListView = (ListView)view.findViewById(R.id.listView);
        btnEnvironment = (Button)view.findViewById(R.id.environment_btn);
        btnEvent  = (Button)view.findViewById(R.id.event_btn);
        btnFestival  = (Button)view.findViewById(R.id.festival_btn);
        btnTraffic = (Button)view.findViewById(R.id.traffic_btn);
        btnWelfare = (Button)view.findViewById(R.id.welfare_btn);
        btnHouse = (Button)view.findViewById(R.id.housing_btn);

        mAdapter = new HomeListViewAdapter();
        mListView.setAdapter(mAdapter);
        mContext = getActivity();

        btnEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "news");
                startActivity(intent);
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "event");
                startActivity(intent);
            }
        });

        btnFestival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "festival");
                startActivity(intent);
            }
        });

        btnTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "traffic");
                startActivity(intent);
            }
        });

        btnWelfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "welfare");
                startActivity(intent);
            }
        });

        btnHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubHomeActivity.class);
                intent.putExtra("category", "house");
                startActivity(intent);
            }
        });
        return view;
    }

}


