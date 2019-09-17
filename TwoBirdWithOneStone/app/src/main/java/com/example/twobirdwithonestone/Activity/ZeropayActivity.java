package com.example.twobirdwithonestone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.twobirdwithonestone.Fragment.AccountFragment;
import com.example.twobirdwithonestone.R;

public class ZeropayActivity extends AppCompatActivity {
    TextView tvBeforeTransform;
    TextView tvHowMuchTransform;
    TextView tvAfterTransform;
    TextView tvPointBeforeTransform;
    TextView tvPointHowMuchTransform;
    TextView tvPointAfterTransform;
    Button btnTransform100;
    Button btnTransform500;
    Button btnTransform1000;
    Button btnTransform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeropay);
        tvBeforeTransform = findViewById(R.id.tvBeforeTransform);
        tvHowMuchTransform = findViewById(R.id.tvHowMuchTransform);
        tvAfterTransform = findViewById(R.id.tvAfterTransform);
        tvPointBeforeTransform = findViewById(R.id.tvPointBeforeTransform);
        tvPointHowMuchTransform = findViewById(R.id.tvPointHowMuchTransform);
        tvPointAfterTransform = findViewById(R.id.tvPointAfterTransform);
        btnTransform100 = findViewById(R.id.btnTransForm100);
        btnTransform500 = findViewById(R.id.btnTransForm500);
        btnTransform1000 = findViewById(R.id.btnTransForm1000);
        btnTransform = findViewById(R.id.btnTransform);
        btnTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("PointBeforeTransform", tvPointBeforeTransform.getText().toString());
                bundle.putString("PointHowMuchTransform", tvPointHowMuchTransform.getText().toString());
                bundle.putString("PointAfterTransform", tvPointAfterTransform.getText().toString());
//                AccountFragment myAccount = new AccountFragment();
//                myAccount.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, myAccount)
//                        .commit();
                finish();
            }
        });
    }
}
