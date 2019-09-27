package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubHomeActivity extends AppCompatActivity {
    private ListView mListView;
    private HomeListViewAdapter mAdapter;

    private Drawable image = null;
    private String title = null;
    private String subtitle = null;
    private String coin = null;
    private String getId = null;
    //String melon_chart_url = "https://www.melon.com/chart/";
    // ?fetchStart=1로 페이지 라우팅을 실행
    private String seoul_news_url = "https://www.seoul.go.kr/realmnews/in/list.do";
    private String seoul_event_url = "https://www.seoul.go.kr/eventreqst/list.do";
    private String seoul_festival_url = "https://www.seoul.go.kr/thismteventfstvl/list.do";
    private String seoul_traffic_url = "https://news.seoul.go.kr/traffic/news-all/page/";
    private String seoul_welfare_url = "https://news.seoul.go.kr/welfare/news-all/page/";
    private String seoul_house_url = "https://www.i-sh.co.kr/main/lay2/program/S1T294C295/www/brd/m_241/list.do";
    private String seoul_house_page_url = "https://www.i-sh.co.kr/main/lay2/program/S1T294C295/www/brd/m_241/view.do?seq=";
    private int nextPage = 1;
    private SeoulBoardCrawler seoulBoardCrawler;
    private SeoulCategoryCrawler seoulCategoryCrawler;
    private SeoulHouseCrawler seoulHouseCrawler;

    private String categoryText;
    boolean lastitemVisibleFlag = false;
    //화면에 리스트의 마지막 아이템이 보여지는지 체크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subhome);

        TextView categoryTextView = (TextView)findViewById(R.id.categoryTextView);

        Intent intent = getIntent();
        categoryText = intent.getExtras().getString("category");

        switch (categoryText){
            case "traffic":
                categoryTextView.setText("교통") ;
                seoulCategoryCrawler = new SeoulCategoryCrawler(seoul_traffic_url);
                seoulCategoryCrawler.execute();
                break;
            case "house":
                categoryTextView.setText("주택") ;
                seoulHouseCrawler = new SeoulHouseCrawler(seoul_house_url);
                seoulHouseCrawler.execute();
                break;
            case "welfare":
                categoryTextView.setText("복지") ;
                seoulCategoryCrawler = new SeoulCategoryCrawler(seoul_welfare_url);
                seoulCategoryCrawler.execute();
                break;
            case "news":
                categoryTextView.setText("소식") ;
               seoulBoardCrawler = new SeoulBoardCrawler(seoul_news_url);
                seoulBoardCrawler.execute();
                break;
            case "festival":
                categoryTextView.setText("행사 및 축제") ;
                seoulBoardCrawler = new SeoulBoardCrawler(seoul_festival_url);
                seoulBoardCrawler.execute();
                break;
            case "event":
                categoryTextView.setText("이벤트 신청") ;
                seoulBoardCrawler = new SeoulBoardCrawler(seoul_event_url);
                seoulBoardCrawler.execute();
                break;
        }
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new HomeListViewAdapter();

        mListView.setAdapter(mAdapter);


        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    //Toast.makeText(SubHomeActivity.this,"바닥에 닿았어요!.",Toast.LENGTH_LONG).show();
                    nextPage++;
                    switch (categoryText){
                        case "news":
                            seoulBoardCrawler = new SeoulBoardCrawler(seoul_news_url);
                            seoulBoardCrawler.execute();
                            break;
                        case "event":
                            seoulBoardCrawler = new SeoulBoardCrawler(seoul_event_url);
                            seoulBoardCrawler.execute();
                        case "festival":
                            seoulBoardCrawler = new SeoulBoardCrawler(seoul_festival_url);
                            seoulBoardCrawler.execute();
                            break;
                        case "welfare":
                            seoulCategoryCrawler = new SeoulCategoryCrawler(seoul_welfare_url);
                            seoulCategoryCrawler.execute();
                            break;
                        case "house":
                            seoulHouseCrawler = new SeoulHouseCrawler(seoul_house_url);
                            seoulHouseCrawler.execute();
                            break;
                    }
                }
            }
        });


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
        ArrayList<String> listAllSpanItem = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        ArrayList<HomeListViewItem> viewItems = new ArrayList<>();
        String crawledUrl;
        private SeoulBoardCrawler(String crawledUrl){
            this.crawledUrl = crawledUrl;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = crawledUrl + "?fetchStart=" + nextPage;
                Document doc = Jsoup.connect(url).get();
                //final Elements rank_list1 = doc.select("div.wrap_song_info div.ellipsis.rank01 span a");
                final Elements crawledSpanItem = doc.select("div.item span");
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
                            listImgUrl.add(element.attr("src"));
                        }
                        System.out.println("번 "+listImgUrl.size());
                        String tmp = "default";
                        int imgUrlIter = 0;
                        int iter = 0;

                        for(Element element: crawledSpanItem) {
                            listAllSpanItem.add(element.attr("class"));
                        }
                        for(int i = 0; i < listAllSpanItem.size(); i++){
                            if(listAllSpanItem.get(i).equals("tbx")){
                                HomeListViewItem item = new HomeListViewItem();
                                item.setImgUrl(tmp);
                                item.setTitle(listTitle.get(iter));
                                item.setContent(listContent.get(iter));
                                item.setDate(listDate.get(iter));
                                item.setUrl(listUrl.get(iter));
                                tmp = "default";
                                viewItems.add(item);
                                iter++;
                            }else {
                                tmp = listImgUrl.get(imgUrlIter++);
                            }
                        }
                        for (int i = 0; i < viewItems.size() ; i++) {
                            mAdapter.addItem(viewItems.get(i));
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

    /*
        작성자 : 박규영
        날짜 : 2019 - 09 - 24
        내용 : 서울 교통 홈페이지 새 소식 크롤러
     */
    private class SeoulCategoryCrawler extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listDate = new ArrayList<>();
        ArrayList<String> listContent = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        ArrayList<HomeListViewItem> viewItems = new ArrayList<>();
        HashMap<String,String> sub_img = new HashMap<>();
        String crawledUrl;
        private SeoulCategoryCrawler(String crawledUrl){
            this.crawledUrl = crawledUrl;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = crawledUrl + nextPage;
                Document doc = Jsoup.connect(url).get();
                final Elements crawledDate = doc.select("div.post-lst div.child_policyDL_R span.time");
                final Elements crawledTxt = doc.select("div.post-lst div.child_policyDL_R");
                final Elements crawledUrl = doc.select("div.post-lst div.child_policyDL_R h3 a");
                final Elements crawledImgUrl = doc.select("div.post-lst div.child_policyDL_l img");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: crawledUrl) {
                            listTitle.add(element.attr("title"));
                            sub_img.put(element.attr("title"),"default");
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
                            listImgUrl.add(element.attr("src"));
                            sub_img.put(element.attr("alt"),element.attr("src"));
                        }
                        for (int i = 0; i < listTitle.size() ; i++) {
                            HomeListViewItem item = new HomeListViewItem();
                            item.setImgUrl(sub_img.get(listTitle.get(i)));
                            item.setTitle(listTitle.get(i));
                            item.setContent(listContent.get(i));
                            item.setDate(listDate.get(i));
                            item.setUrl(listUrl.get(i));
                            mAdapter.addItem(item);
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

    /*
        작성자 : 박규영
        날짜 : 2019 - 09 - 24
        내용 : 서울 주택 홈페이지 새 소식 크롤러
     */
    private class SeoulHouseCrawler extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listDate = new ArrayList<>();
        ArrayList<String> listContent = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        ArrayList<HomeListViewItem> viewItems = new ArrayList<>();
        String crawledUrl;
        private SeoulHouseCrawler(String crawledUrl){
            this.crawledUrl = crawledUrl;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = crawledUrl +"?page=" +nextPage;
                Document doc = Jsoup.connect(url).get();
                final Elements crawledDate = doc.select("tbody tr td.num");
                //url 과 내용, contents
                final Elements crawledUrl = doc.select("tbody tr td.txtL a");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: crawledUrl) {
                            listTitle.add(element.text());
                            listImgUrl.add(("default"));
                        }
                        int k = 1;
                        for(Element element: crawledDate) {
                            if(k%2 != 0) {
                                listDate.add(element.text());
                                k++;
                            }else {
                                k++;
                                continue;
                            }
                        }
                        for(Element element: crawledUrl) {
                            listContent.add(element.text());
                        }
                        for(Element element: crawledUrl) {
                            String value = element.attr("onclick").split("'")[1];
                            listUrl.add(seoul_house_page_url+value);
                        }
                        for (int i = 0; i < listTitle.size() ; i++) {
                            HomeListViewItem item = new HomeListViewItem();
                            item.setImgUrl(listImgUrl.get(i));
                            item.setTitle(listTitle.get(i));
                            item.setContent(listContent.get(i));
                            item.setDate(listDate.get(i));
                            item.setUrl(listUrl.get(i));
                            mAdapter.addItem(item);
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
