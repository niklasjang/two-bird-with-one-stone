package com.example.twobirdwithonestone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.ZeropayActivity;
import com.example.twobirdwithonestone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AccountFragment extends Fragment {
    private static final int REQUEST_ZEROPAY = 200;
    private FirebaseFirestore db;
    Button btn_zeropay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btn_zeropay = view.findViewById(R.id.zeropay_bnt);
        btn_zeropay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ZeropayActivity.class);
                startActivityForResult(intent,REQUEST_ZEROPAY);
            }
        });

        //DB의 User정보 가져와서 보여주기
        final TextView tvUserPoint = view.findViewById(R.id.tvUserPoint);
        final TextView tvUserName = view.findViewById(R.id.tvUserName);
        db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    tvUserPoint.setText(snapshot.get("point").toString());
                    tvUserName.setText(snapshot.get("uid").toString());
                    Log.d(TAG, "Current data: " + snapshot.get("point"));
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
        //
        return view;
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ZEROPAY){
            String strPointBeforeTransform = getArguments().getString("PointBeforeTransform");
            String strPointHowMuchTransform = getArguments().getString("PointHowMuchTransform");
            String strPointAfterTransform = getArguments().getString("PointAfterTransform");
        }
    }*/
}
