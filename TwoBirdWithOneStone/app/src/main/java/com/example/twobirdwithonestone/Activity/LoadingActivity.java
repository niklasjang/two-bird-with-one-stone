package com.example.twobirdwithonestone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.twobirdwithonestone.R;
import android.os.Bundle;
import android.os.Handler;

/***** LoadingActivity 2019-09-08 3AM Hz*****
 * 앱 시작시 나오는 화면 (activity_loading.xml)
 * 화면을 사용자에게 delayMillis(3000으로 지정) 만큼 보여줌
 * 상태바(styles.xml -> name="colorPrimaryDark")의 색상 변경
 */

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
