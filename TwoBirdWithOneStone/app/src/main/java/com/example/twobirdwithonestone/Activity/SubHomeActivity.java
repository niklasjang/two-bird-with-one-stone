package com.example.twobirdwithonestone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.TextView;

import com.example.twobirdwithonestone.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubHomeActivity extends AppCompatActivity {
    private ListView mListView;
    private HomeListViewAdapter mAdapter;

    private Drawable image = null;
    private String title = null;
    private String subtitle = null;
    private String coin = null;
    private String getId = null;
    //String melon_chart_url = "https://www.melon.com/chart/";
    private String seoul_url = "https://www.seoul.go.kr/realmnews/in/list.do";

    private String categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subhome);

        TextView categoryTextView = (TextView)findViewById(R.id.categoryTextView);

        Intent intent = getIntent();
        categoryText = intent.getExtras().getString("category");
        categoryTextView.setText(categoryText) ;

        SeoulBoardCrawler seoulBoardCrawler = new SeoulBoardCrawler();
        seoulBoardCrawler.execute();
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new HomeListViewAdapter();

        mListView.setAdapter(mAdapter);

    }

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
                            if(listDate.get(i).contains(categoryText)){
                                mAdapter.addItem(mapImgUrl.get(listTitle.get(i)) , listTitle.get(i), listContent.get(i), listDate.get(i), listUrl.get(i));
                            }
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
