package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

public class SubshopActivity extends AppCompatActivity {
    Button purchase_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_subshop);

        purchase_btn = findViewById(R.id.purchase_btn);
        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubshopActivity.this,CreateQR.class);
                startActivity(intent);
            }
        });

    }
}