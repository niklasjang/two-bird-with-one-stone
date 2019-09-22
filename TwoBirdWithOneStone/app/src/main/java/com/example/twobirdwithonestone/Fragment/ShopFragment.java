package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.Items;
import com.example.twobirdwithonestone.Activity.ShopGridViewAdapter;
import com.example.twobirdwithonestone.Activity.ShopListViewActivity;
import com.example.twobirdwithonestone.R;

import java.io.ByteArrayOutputStream;
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
        View view =  inflater.inflate(R.layout.fragment_gridshop, container, false);
        mGridView = (GridView)view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopGridViewAdapter();

        mGridView.setAdapter(mAdapter);
        mContext = getContext();

        //position 별로 전달 값 나누기
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //문화생활
                if ( i == 0) {

                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    Bitmap sendBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cake1);
                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    sendBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                    byte[] byteArray1 = stream1.toByteArray();
                    Bitmap sendBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cakec);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    sendBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();


                    ArrayList<Items> culture_list = new ArrayList<Items>();
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                //커피음료
                else if ( i == 1) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    Bitmap sendBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cake1);
                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    sendBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                    byte[] byteArray1 = stream1.toByteArray();
                    Bitmap sendBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_cash);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    sendBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();

                    ArrayList<Items> culture_list = new ArrayList<Items>();
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    culture_list.add(new Items(byteArray2,"문화생활","경복궁 야간개장 입장권","3000","설명설명설명"));
                    //parcelable로 arraylist 넘기기
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                //아이스크림
                else if ( i == 2) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                //케이크
                else if ( i == 3) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                //기부
                else if ( i == 4) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                //봉사
                else if ( i == 5) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                }

        });

        //상품 category arraylist 생성
        ArrayList<String> listTitle = new ArrayList<String>();
        listTitle.add("문화생활"); listTitle.add("커피/음료"); listTitle.add("아이스크림"); listTitle.add("케이크"); listTitle.add("기부");
        listTitle.add("기타");
        //상품 category image arraylist 생성
        ArrayList<Drawable> listIcon = new ArrayList<Drawable>();
        //arraylist에 drawable 이미지 추가
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_ticket));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_coffee));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_icecream3));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_cakec));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_fund));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_tent));
        //gridview에 gridview item 루프돌며 생성
        for(int i=0; i<listTitle.size();i++){
            mAdapter.addItem(listIcon.get(i),listTitle.get(i));
        }
        return view;
    }
}
