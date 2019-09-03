package com.example.twobirdwithonestone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.R;
import com.example.twobirdwithonestone.Service.LockScreenService;

public class SettingsFragment extends Fragment {

    Boolean boolLockScreen = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
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
                intent.putExtra("LockScreen", boolLockScreen);
                getActivity().startService(intent);
                //Toast.makeText(getContext(), "SETTINGS에서 START SERVICE", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
