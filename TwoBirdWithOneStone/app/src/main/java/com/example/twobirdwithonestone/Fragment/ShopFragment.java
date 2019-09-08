package com.example.twobirdwithonestone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.twobirdwithonestone.Activity.PopupActivity;
import com.example.twobirdwithonestone.Activity.SubshopActivity;
import com.example.twobirdwithonestone.R;

public class ShopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_shop, container, false);
        LinearLayout button = (LinearLayout) rootview.findViewById(R.id.coffee_btn1);
        button.setOnClickListener(new View.OnClickListener() {

                                      public void onClick(View view) { Intent intent = new Intent(getActivity(), SubshopActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        LinearLayout button2 = (LinearLayout) rootview.findViewById(R.id.culture_btn1);
        button2.setOnClickListener(new View.OnClickListener() {

                                       public void onClick(View view) { Intent intent = new Intent(getActivity(), PopupActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );
        return rootview;
    }

}