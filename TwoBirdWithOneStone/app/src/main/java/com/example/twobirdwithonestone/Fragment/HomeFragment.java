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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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
import com.example.twobirdwithonestone.Activity.ShopGridViewAdapter;
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
    private GridView mGridView;
    private ShopGridViewAdapter mAdapter;
    private Drawable image = null;
    private String title = null;
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

        mGridView = (GridView)view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopGridViewAdapter();

        mGridView.setAdapter(mAdapter);
        mContext = getContext();

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

}


