package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

public class SubsubshopActivity extends AppCompatActivity {
    Button purchase_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsubshop);

        purchase_btn = findViewById(R.id.purchase_btn);
        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivity(intent);
            }
        });

    }
}