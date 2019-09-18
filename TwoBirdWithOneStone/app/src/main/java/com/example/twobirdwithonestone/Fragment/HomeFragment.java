package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.twobirdwithonestone.Activity.HomeListViewAdapter;
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
    //String melon_chart_url = "https://www.melon.com/chart/";
    private String seoul_url = "https://www.seoul.go.kr/realmnews/in/list.do";

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        SeoulBoardCrawler seoulBoardCrawler = new SeoulBoardCrawler();
        seoulBoardCrawler.execute();
        mListView = (ListView)view.findViewById(R.id.listView);
        mAdapter = new HomeListViewAdapter();

        mListView.setAdapter(mAdapter);

        //mAdapter.addItem();
        /**
         * listView.setAdapter(adapter);
         *
         * adapter 부분이 에러...
         * https://recipes4dev.tistory.com/63?category=643521 참고해서 수정하기
         */

        mContext = getActivity();


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
        title = "제목";
        subtitle = "부제";
        coin = "1000";
        image = (Drawable) ContextCompat.getDrawable(mContext, R.drawable.circle_logo);

        return view;
    }

    /*
    작성자 : 박규영
    날짜 : 2019 - 09 - 12
    내용 : 서울 분야별 새소식 크롤러로, HomeListViewAdapter , HomeListViewItem과 관련이 있다.
     */
    private class SeoulBoardCrawler extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listDate = new ArrayList<>();
        ArrayList<String> listContent = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();
        Map<String , String> mapImgUrl = new HashMap<String , String>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(seoul_url).get();
                //final Elements rank_list1 = doc.select("div.wrap_song_info div.ellipsis.rank01 span a");
                final Elements crawledSubject = doc.select("span.tbx em.subject");
                final Elements crawledDate = doc.select("span.tbx em.date");
                final Elements crawledTxt = doc.select("span.tbx em.txt");
                final Elements crawledUrl = doc.select("div.item a");
                final Elements crawledImgUrl = doc.select("div.item img");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: crawledSubject) {
                            String res = "";
                            String[] splitedTitle = element.text().split(" ");
                            for(int i = 1; i < splitedTitle.length; i++) {
                                if(i == 1){
                                    res =res + splitedTitle[i];
                                }else{
                                    res =res + " " + splitedTitle[i];
                                }
                            }
                            listTitle.add(res);
                            mapImgUrl.put(res,"default");
                        }
                        for(Element element: crawledDate) {
                            listDate.add(element.text());
                        }
                        for(Element element: crawledTxt) {
                            listContent.add(element.text());
                        }
                        for(Element element: crawledUrl) {
                            listUrl.add(element.attr("href"));
                        }
                        for(Element element: crawledImgUrl) {
                            mapImgUrl.put(element.attr("alt"),element.attr("src"));
                        }

                        for (int i = 0; i < listTitle.size() ; i++) {
                            mAdapter.addItem(mapImgUrl.get(listTitle.get(i)) , listTitle.get(i), listContent.get(i), listDate.get(i), listUrl.get(i));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}


