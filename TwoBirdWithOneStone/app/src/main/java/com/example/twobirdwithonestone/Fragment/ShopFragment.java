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


                if ( i == 0) {

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
                    culture_list.add(new Items(byteArray2,"경복궁 야간개장 입장권","3000"));
                    culture_list.add(new Items(byteArray1,"따릉이 이용권","2000"));
                    culture_list.add(new Items(byteArray2,"코엑스 아쿠아리움 입장권","15000"));
                    culture_list.add(new Items(byteArray2,"롯데월드타워 전망대","20000"));
                    culture_list.add(new Items(byteArray2,"서울대공원 동물원 입장권","3000"));
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                if ( i == 1) {
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
                    culture_list.add(new Items(byteArray2,"경복궁 야간개장 입장권","3000"));
                    culture_list.add(new Items(byteArray1,"따릉이 이용권","2000"));
                    culture_list.add(new Items(byteArray2,"코엑스 아쿠아리움 입장권","15000"));
                    culture_list.add(new Items(byteArray2,"롯데월드타워 전망대","20000"));
                    culture_list.add(new Items(byteArray2,"서울대공원 동물원 입장권","3000"));
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                if ( i == 2) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);

                    startActivity(intent);
                }
                if ( i == 3) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                if ( i == 4) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                if ( i == 5) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                }

        });


        ArrayList<String> listTitle = new ArrayList<String>();
        listTitle.add("문화생활"); listTitle.add("커피/음료"); listTitle.add("아이스크림"); listTitle.add("케이크"); listTitle.add("뷰티");
        listTitle.add("기타");
        ArrayList<Drawable> listIcon = new ArrayList<Drawable>();

        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_ticket));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_coffee));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_icecream3));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_cakec));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_beauty));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_tent));

        for(int i=0; i<listTitle.size();i++){
            mAdapter.addItem(listIcon.get(i),listTitle.get(i));
        }
        return view;
    }
}
