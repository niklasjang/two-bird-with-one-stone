package com.example.twobirdwithonestone.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

import com.example.twobirdwithonestone.Fragment.AccountFragment;
import com.example.twobirdwithonestone.Fragment.HomeFragment;
import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Fragment.SettingsFragment;
import com.example.twobirdwithonestone.Fragment.ShopFragment;
import com.example.twobirdwithonestone.Service.LockScreenService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    boolean doubleBackToExitPressedOnce = false;
    EditText editText;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_shop:
                    selectedFragment = new ShopFragment();
                    break;
                case R.id.navigation_account:
                    selectedFragment = new AccountFragment();
                    break;
                case R.id.navigation_settings:
                    selectedFragment = new SettingsFragment();
                    break;
                default:
                    selectedFragment = new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove  SKT/Sounde/Battery/Time title on top of screeen
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        //To hide app_name title bar, go to res/values/style. And then change DarkActionBar to NoActionBar.

        setContentView(R.layout.activity_main);

        //***** Start LoadingActivity 2019-09-08 3AM Hz *****
        Intent intent = new Intent(this, LoadingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        //***** Finish LoadingActivity *****

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        //Main이 생성되면 바로 LockScreenService를 실행시킨다.
        intent = new Intent(this, LockScreenService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startService(intent);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            //앱을 종료하지 않고 홈 화면을 띄운다.
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "종료하시려면 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
