package com.example.twobirdwithonestone.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.twobirdwithonestone.R;

public class SettingPopup extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_popup);

        TextView settingTitleTextView = (TextView)findViewById(R.id.setting_title_text);
        TextView settingMainTextView = (TextView)findViewById(R.id.setting_main_text);

        Intent intent = getIntent();

        String settingText = intent.getExtras().getString("settingText");

        switch (settingText){
            case"notice":
                settingTitleTextView.setText("공지사항");
                settingMainTextView.setText("추가된 공지사항이 없습니다");
                break;
            case"question":
                settingTitleTextView.setText("문의하기");
                settingMainTextView.setText("이용시 불편하신 점을 자세하게 작성하여\n" +
                        "soo7652@naver.com 으로 보내주세요.\n 확인 후 답변 드리겠습니다");
                break;
            case"faq":
                settingTitleTextView.setText("FAQ");
                settingMainTextView.setText("Q. 꿩먹알먹은 무슨 뜻인가요?\n" +
                        "A. 서울시 관련 정보도 얻고 포인트도 적립해 다양한 혜택을 받는 것을 의미합니다!");
                break;
            case"introduce":
                settingTitleTextView.setText("꿩먹알먹 사용방법");
                settingMainTextView.setText("영상주소 첨부");
                break;
            case "developer":
                settingTitleTextView.setText("꿩먹알먹 사용방법");
                settingMainTextView.setText("박규영(gyoue200125@gmail.com):  Back-end\n" +
                        "장환석(niklasjang@gmail.com):  Back-end\n" +
                        "박혜지(kauphj17@gmail.com):  UI, Front-end\n" +
                        "천수환(su7651@gmail.com):  Front-end");
                break;

            case"copyright":
                settingTitleTextView.setText("저작권 출처");
                settingMainTextView.setText("글꼴\n이 어플리케이션은 포천 오성과 한음체R를 사용하여 디자인 되었습니다.\n\n" +
                        "아이콘\nIcon made by Freepik from www.flaticon.com\nIcon made by Good Ware from www.flaticon.com\n" +
                        "Icon made by Nhor Phai from www.flaticon.com\nIcon made by Pixelmeetup from www.flaticon.com\n" +
                        "Icon made by Laymik from Noun Project");
                break;

        }

    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}