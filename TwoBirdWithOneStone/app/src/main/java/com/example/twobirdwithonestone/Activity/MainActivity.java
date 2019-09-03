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

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        editText = (EditText) findViewById(R.id.editText);
    }

    public void onButton1Clicked(View v){
        String name = editText.getText().toString();
        Intent intent = new Intent(this, LockScreenService.class);
        intent.putExtra("command", "show");
        intent.putExtra("name", name);
        startService(intent);
    }

}
