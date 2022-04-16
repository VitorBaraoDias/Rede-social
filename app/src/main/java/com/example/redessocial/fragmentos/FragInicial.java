package com.example.redessocial.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.redessocial.ActivityMenu;
import com.example.redessocial.R;

public class FragInicial extends Fragment {

    private View view;
    private ImageView imgDms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int id_user = getArguments().getInt("id");
        view = inflater.inflate(R.layout.fragment_frag_inicial, container, false);
        setObj();


        imgDms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity mainActivity = getActivity();

                if(mainActivity instanceof ActivityMenu)
                    ((ActivityMenu) mainActivity).setCurrentItemPager(2); // 1 = ID do fragment
            }
        });
        return view;
    }

    private void setObj(){
        imgDms = view.findViewById(R.id.imgDms);
    }
}