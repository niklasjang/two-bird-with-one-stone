package com.example.twobirdwithonestone.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.twobirdwithonestone.Fragment.HomeFragment;
import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Service.LockScreenService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QuerySnapshot;

public class LockScreenActivity extends AppCompatActivity {
    private ViewFlipper v_fllipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(
//            // 기본 잠금화면보다 우선출력
//            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock_screen);
        findViewById(R.id.btn_lock_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //포인트 증가
                final DataBase db = new DataBase();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                db.registerUserData("Users",uid).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        db.updateUserPoint("Users", FirebaseAuth.getInstance().getCurrentUser().getUid(),2);
                    }
                });
//                String url ="https://www.seoul.go.kr/main/index.jsp";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.btn_un_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //앱을 종료하지 않고 홈 화면을 띄운다.
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            }
        });

        int images[] = {
                R.drawable.ic_lock_screen_ad_1, R.drawable.ic_lock_screen_ad_2, R.drawable.ic_lock_screen_ad_3,
                R.drawable.ic_lock_screen_ad_4, R.drawable.ic_lock_screen_ad_5, R.drawable.ic_lock_screen_ad_6, R.drawable.ic_lock_screen_ad_7
        };

        v_fllipper = findViewById(R.id.vfLockScreen);

        for(int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image);
            v_fllipper.addView(imageView);
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        v_fllipper.setInAnimation(in);
        v_fllipper.setOutAnimation(out);
        // set interval time for flipping between views
        v_fllipper.setFlipInterval(10000);
        // set auto start for flipping between views
        v_fllipper.setAutoStart(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HOME){
            Toast.makeText(this, "홈으로 가게?", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
