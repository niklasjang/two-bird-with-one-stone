package com.example.twobirdwithonestone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Service.LockScreenService;

public class LockScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
            // 기본 잠금화면보다 우선출력
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock_screen);
        findViewById(R.id.btn_lock_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭하면 잠금화면 종료
                finish();
            }
        });
    }
}
