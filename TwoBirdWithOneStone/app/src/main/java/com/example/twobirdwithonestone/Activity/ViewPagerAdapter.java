package com.example.twobirdwithonestone.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> imageList;

    public ViewPagerAdapter(Context context, ArrayList<Integer> imageList)
    {
        this.mContext = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager_item, null);
        final int pos = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pos){
                    case 0:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://mediahub.seoul.go.kr/archives/1252464?tr_code=snews")));
                        break;
                    case 1:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/welfare/archives/509014?tr_code=short")));
                        break;
                    case 2:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://mediahub.seoul.go.kr/archives/1253515?tr_code=snews")));
                        break;
                    case 3:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/welfare/archives/509734")));
                        break;
                    case 4:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://mediahub.seoul.go.kr/archives/1252464?tr_code=snews")));
                        break;
                    case 5:
                        //링크 부탁
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/citybuild/archives/505708")));
                        break;
                    case 6:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/env/archives/504088")));
                        break;
                    case 7:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/traffic/archives/502331")));
                        break;
                    case 8:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/culture/archives/503977")));
                        break;
                    case 9:
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.seoul.go.kr/welfare/archives/509749")));
                        break;
                }
            }
        });

        ImageView imageView = view.findViewById(R.id.viewpager_imgview);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View)o);
    }
}
