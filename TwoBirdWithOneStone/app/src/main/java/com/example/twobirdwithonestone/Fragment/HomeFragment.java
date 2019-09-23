package com.example.twobirdwithonestone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.HomeListViewAdapter;
import com.example.twobirdwithonestone.Activity.SubHomeActivity;
import com.example.twobirdwithonestone.R;

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
        mContext = getActivity();

        /*
        작성자 : 박혜지
        날짜 : 2019 - 09 - 22
        내용 : 홈화면에있는 카테고리와 연동시킨 버튼 8개
        */

        Button btnTransportation = (Button) view.findViewById(R.id.transportation_btn);
        btnTransportation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","교통");
                startActivity(intent);
            }
        });
        Button btnSafety = (Button) view.findViewById(R.id.safety_btn);
        btnSafety.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","안전");
                startActivity(intent);
            }
        });
        Button btnHousing = (Button) view.findViewById(R.id.housing_btn);
        btnHousing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","주택");
                startActivity(intent);
            }
        });
        Button btnEconomy = (Button) view.findViewById(R.id.economy_btn);
        btnEconomy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","경제");
                startActivity(intent);
            }
        });
        Button btnEnvironment = (Button) view.findViewById(R.id.environment_btn);
        btnEnvironment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","환경");
                startActivity(intent);
            }
        });
        Button btnCulture = (Button) view.findViewById(R.id.culture_btn);
        btnCulture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","문화");
                startActivity(intent);
            }
        });
        Button btnWelfare = (Button) view.findViewById(R.id.welfare_btn);
        btnWelfare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","복지");
                startActivity(intent);
            }
        });
        Button btnAdministrative = (Button) view.findViewById(R.id.administrative_btn);
        btnAdministrative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubHomeActivity.class);
                intent.putExtra("category","행정");
                startActivity(intent);
            }
        });

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


