package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.HomeListViewAdapter;
import com.example.twobirdwithonestone.Activity.Items;
import com.example.twobirdwithonestone.Activity.ShopGridViewAdapter;
import com.example.twobirdwithonestone.Activity.SubshopActivity;
import com.example.twobirdwithonestone.R;

import java.util.ArrayList;



public class ShopFragment extends Fragment {
    private GridView mGridView;
    private ShopGridViewAdapter mAdapter;

    private Drawable image = null;
    private String title = null;

    private Context mContext;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);

        mGridView = (GridView)view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopGridViewAdapter();

        mGridView.setAdapter(mAdapter);
        mContext = getContext();


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if ( i == 1) {


                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    ArrayList<Items> coffee_list = new ArrayList<Items>();
                    coffee_list.add(new Items(R.drawable.ic_coffee,"coffee","3000"));
                    coffee_list.add(new Items(R.drawable.ic_coffee,"coffee","3000"));
                    coffee_list.add(new Items(R.drawable.ic_coffee,"coffee","3000"));
                    coffee_list.add(new Items(R.drawable.ic_coffee,"coffee","3000"));
                    intent.putParcelableArrayListExtra("coffee_list", coffee_list);
                    startActivity(intent);
                }
                if ( i == 2) {
                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    startActivity(intent);
                }
                if ( i == 3) {
                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    startActivity(intent);
                }
                if ( i == 4) {
                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    startActivity(intent);
                }
                if ( i == 5) {
                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    startActivity(intent);
                }
                if ( i == 6) {
                    Intent intent = new Intent(getActivity(), SubshopActivity.class);
                    startActivity(intent);
                }
                }

        });


        ArrayList<String> listTitle = new ArrayList<String>();
        listTitle.add("아이스크림"); listTitle.add("2"); listTitle.add("3"); listTitle.add("4"); listTitle.add("5");
        listTitle.add("6");
        ArrayList<Drawable> listIcon = new ArrayList<Drawable>();

        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_culture1));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_coffee));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_cake1));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_icecream));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_icecream));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.circle_logo));

        for(int i=0; i<listTitle.size();i++){
            mAdapter.addItem(listIcon.get(i),listTitle.get(i));
        }
        return view;
    }
}
