package com.example.twobirdwithonestone.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SubShopListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private SubShopListViewAdapter mAdapter;
    private FirebaseFirestore db;
    private Date currentTime;
    private String currentUID;
    private long userPoint = 0;
    private String itemPrice;
    private DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sublistshop);
        mListView = (ListView)findViewById(R.id.SubShopListview);
        mAdapter = new SubShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //ShopListViewActivity에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();
        //ArrayList 를 받아 addItem
        ArrayList<Items> list = intent.getParcelableArrayListExtra("coffee_list");
        for(int i=0;i<list.size();i++) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(i).image, 0, list.get(i).image.length);
            mAdapter.addItem(list.get(i).category, list.get(i).name, bitmap, list.get(i).explanation);
        }

        Button btn_buy_item = findViewById(R.id.btn_buy_item);
        itemPrice = list.get(0).getprice();
        Log.d("SubShopListViewActivity", "햐햐 아이템가격"+itemPrice);
        String buyString = itemPrice+"Point로 구매";
        btn_buy_item.setText(buyString);
        Log.d("123", btn_buy_item.getText().toString());
        db = FirebaseFirestore.getInstance();
        currentTime = Calendar.getInstance().getTime();
        currentUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final CollectionReference couponRef = db.collection("Users").document(currentUID).collection("Coupons");
        btn_buy_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //유저의 포인트 가져오기
                final DocumentReference docRef = db.collection("Users").document(currentUID);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserData userData = documentSnapshot.toObject(UserData.class);
                        userPoint =  userData.getPoint();
                        Toast.makeText(getApplicationContext(), "햐햐 보유 포인트는 "+userPoint, Toast.LENGTH_SHORT).show();
                        //아이템 구매하기
                        if(userPoint != 0){
                            int itemPriceInt = Integer.parseInt(itemPrice);
                            if(userPoint >= itemPriceInt){
                                //유저 Point Update
                                db.collection("Users").document(currentUID)
                                        .update("point", userPoint - itemPriceInt)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "햐햐 유저 포인트 업데이트 성공", Toast.LENGTH_SHORT).show();
                                                //쿠폰 추가하기!
                                                SubShopListViewItem bitem = (SubShopListViewItem)mListView.getItemAtPosition(0);
                                                Log.d("SubShopListview", "SUSU"+ bitem.getTitle());
                                                couponRef.add(new Coupon(currentTime.toString(), "쿠폰", currentUID, false, currentUID))
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Log.d("SubShopListViewActivity", "햐햐 쿠폰 저장 성공");
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.d("SubShopListViewActivity", "햐햐 쿠폰 저장 실패");
                                                            }
                                                        });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "햐햐 유저 포인트 업데이트 실패", Toast.LENGTH_SHORT).show();
                                                Log.w(TAG, "Error updating document", e);
                                            }
                                        });
                            }else{
                                Toast.makeText(getApplicationContext(), "포인트가 부족합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "포인트가 부족합니다.", Toast.LENGTH_SHORT).show();
                            Log.d("SubShopListViewActivity", "O point");
                        }
                    }
                });
            }
        });
    }
}
