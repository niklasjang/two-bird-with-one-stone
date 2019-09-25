package com.example.twobirdwithonestone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.LoginActivity;
import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Service.LockScreenService;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    Boolean boolLockScreen = false;

    public Boolean getBoolLockScreen() {
        return boolLockScreen;
    }
    public void setBoolLockScreen(Boolean boolLockScreen) {
        this.boolLockScreen = boolLockScreen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        //switch
        Switch switchLockScreen = view.findViewById(R.id.switch_lock_screen);
        switchLockScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "SwitchLockScreen 클릭!", Toast.LENGTH_LONG).show();
                if (boolLockScreen) {
                    boolLockScreen = false;
                } else {
                    boolLockScreen = true;
                }
                Intent intent = new Intent(getActivity(), LockScreenService.class);
                //TODO intent.addFlags() 필요할까?
                intent.putExtra("LockScreen", boolLockScreen);
                getActivity().startService(intent);
                //Toast.makeText(getContext(), "SETTINGS에서 START SERVICE", Toast.LENGTH_LONG).show();
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
}
