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
                    //이미지 전달을 위해서 bitmap 이미지를 bytearray로 압축시켜 바꿈
                    //사진 용량 제한이 있음
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);

                    Bitmap sendBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_culture_goong3);
                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    sendBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                    byte[] byteArray1 = stream1.toByteArray();

                    Bitmap sendBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_culture_bike3);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    sendBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();

                    Bitmap sendBitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_culture_aqua);
                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                    sendBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream3);
                    byte[] byteArray3 = stream3.toByteArray();

                    Bitmap sendBitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_culture_seoulpark1);
                    ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                    sendBitmap4.compress(Bitmap.CompressFormat.JPEG, 100, stream4);
                    byte[] byteArray4 = stream4.toByteArray();

                    Bitmap sendBitmap5 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_culture_lottetower1);
                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                    sendBitmap5.compress(Bitmap.CompressFormat.JPEG, 100, stream5);
                    byte[] byteArray5 = stream5.toByteArray();

                    ArrayList<Items> culture_list = new ArrayList<Items>();
                    culture_list.add(new Items(byteArray1,"문화생활","경복궁 야간개장 입장권","3000",getString(R.string.goong),"서울시"));
                    culture_list.add(new Items(byteArray2,"문화생활","따릉이","3000",getString(R.string.bike),"서울시"));
                    culture_list.add(new Items(byteArray3,"문화생활","아쿠아리움","3000",getString(R.string.aqua),"코엑스"));
                    culture_list.add(new Items(byteArray4,"문화생활","서울대공원 동물원","3000",getString(R.string.seoulpark),"서울시"));
                    culture_list.add(new Items(byteArray5,"문화생활","롯데월드 타워 전망대","3000",getString(R.string.lotte),"롯데월드"));
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                //기부
                if ( i == 1) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);

                    Bitmap sendBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_fund_unicef);
                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    sendBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                    byte[] byteArray1 = stream1.toByteArray();

                    Bitmap sendBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_fund_peoplesub);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    sendBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();

                    Bitmap sendBitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_fund_redcross);
                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                    sendBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream3);
                    byte[] byteArray3 = stream3.toByteArray();

                    Bitmap sendBitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_fund_asia);
                    ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                    sendBitmap4.compress(Bitmap.CompressFormat.JPEG, 100, stream4);
                    byte[] byteArray4 = stream4.toByteArray();

                    Bitmap sendBitmap5 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_fund_goodneighbors);
                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                    sendBitmap5.compress(Bitmap.CompressFormat.JPEG, 100, stream5);
                    byte[] byteArray5 = stream5.toByteArray();

                    ArrayList<Items> culture_list = new ArrayList<Items>();
                    culture_list.add(new Items(byteArray1,"기부","개발도상국 어린이 지원","3000",getString(R.string.unicef),"유니세프"));
                    culture_list.add(new Items(byteArray2,"기부","도심 숲 조성과 보존","3000","치유의 숲 조성을 기본목표로 나아가 도심 곳곳에 역사적 콘텐츠를 문화적으로 소개하는 작은 생태공원 조성과 확장","사람숲"));
                    culture_list.add(new Items(byteArray3,"기부","기부금품 모집","3000",getString(R.string.redcross),"대한적십자사"));
                    culture_list.add(new Items(byteArray4,"기부","동화책선물","3000","아시아 도서 출판, 교육, 조사.연구, 교사 연수 사업 - 도서관에 책이 부족한 학교에 도서를 현지어로 번역, 제작하여 보급 - 이중 언어로 번역, 제작하여 국내 다문화 가정 및 외국인 근로자 등 이주민에게 보급","한국아시아우호재단"));
                    culture_list.add(new Items(byteArray5,"기부","국내 사업현장의 아동권리 보호","3000","국내복지사업 1. 아동의 권리보호 및 권익증진을 위한 다양한 사업을 추진하여 아동 및 지역 주민의 삶의 질 향상 및 가족복지 증진\n"+ "2. 학대피해아동보호 및 예방을 위한 전문복지서비스 지원\n"+" 3. 빈곤가정아동 및 가정, 지역사회를 포함한 통합적 사례관리를 통해 전문적 지원체계 마련","굿네이버스"));
                    //parcelable로 arraylist 넘기기
                    intent.putParcelableArrayListExtra("culture_list", culture_list);
                    startActivity(intent);
                }
                //아이스크림
                if ( i == 2) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                //케이크
                if ( i == 3) {
                    Intent intent = new Intent(getActivity(), ShopListViewActivity.class);
                    startActivity(intent);
                }
                //커피/음료
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

        //상품 category arraylist 생성
        ArrayList<String> listTitle = new ArrayList<String>();
        listTitle.add("문화생활"); listTitle.add("기부"); listTitle.add("아이스크림"); listTitle.add("케이크"); listTitle.add("커피/음료");
        listTitle.add("기타");
        //상품 category image arraylist 생성
        ArrayList<Drawable> listIcon = new ArrayList<Drawable>();
        //arraylist에 drawable 이미지 추가
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_ticket));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_fund));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_icecream));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_cake));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_coffee));
        listIcon.add((Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_etc));
        //gridview에 gridview item 루프돌며 생성
        for(int i=0; i<listTitle.size();i++){
            mAdapter.addItem(listIcon.get(i),listTitle.get(i));
        }
        return view;
    }
}
