package com.example.twobirdwithonestone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.DataBase;
import com.example.twobirdwithonestone.Activity.LoginActivity;
import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Service.LockScreenService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SettingsFragment extends Fragment {
    private final String TAG = "SettingsFragment";
    Boolean boolLockScreen = false;
    private FirebaseFirestore db;
    private String currentUID;
    private Switch switchLockScreen;
    public Boolean getBoolLockScreen() {
        return boolLockScreen;
    }
    public void setBoolLockScreen(Boolean boolLockScreen) {
        this.boolLockScreen = boolLockScreen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        switchLockScreen = view.findViewById(R.id.switch_lock_screen);
        //User data fetch. DB의 User정보 가져와기
        final TextView tvUserPoint = view.findViewById(R.id.tvUserPoint);
        //final TextView tvUserName = view.findViewById(R.id.tvUserName);

        final DocumentReference docRef = db.collection("Users").document(currentUID);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    boolLockScreen = (Boolean) snapshot.get("switchLockScreen");
                    switchLockScreen.setChecked(boolLockScreen);
                    Intent intent = new Intent(getActivity(), LockScreenService.class);
                    intent.putExtra("LockScreen", boolLockScreen);
                    getActivity().startService(intent);
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        //switch
        switchLockScreen = view.findViewById(R.id.switch_lock_screen);
        //초기상태를 결정한다.
        switchLockScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "SwitchLockScreen 클릭!", Toast.LENGTH_LONG).show();
                if (boolLockScreen) {
                    boolLockScreen = false;
                    db.collection("Users").document(currentUID).update("switchLockScreen", false);

                } else {
                    boolLockScreen = true;
                    db.collection("Users").document(currentUID).update("switchLockScreen", true);
                }
                Intent intent = new Intent(getActivity(), LockScreenService.class);
                intent.putExtra("LockScreen", boolLockScreen);
                getActivity().startService(intent);
                //Firebase 연동
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(getContext(), "updateUserSwitchLockScreen  성공", Toast.LENGTH_SHORT).show();
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getContext(), "updateUserSwitchLockScreen  실패", Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });

        //logout button , 2019,9,7 gyu-young
        Button btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();

    }
}
