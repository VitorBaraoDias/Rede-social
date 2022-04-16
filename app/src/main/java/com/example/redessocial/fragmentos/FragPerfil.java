package com.example.redessocial.fragmentos;

import static android.app.Activity.RESULT_OK;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.redessocial.R;
import com.example.redessocial.bd.dao.UserDao;
import com.example.redessocial.objetos.User;

import java.net.URI;

public class FragPerfil extends Fragment {

    private View view;
    private TextView txtName;
    private ImageView imgPickProfile;

    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_frag_profile, container, false);
        setObj();
        UserDao userDao = new UserDao(getContext());
        int id_user = getArguments().getInt("id");

        setName(id_user,userDao);
        imgPickProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private void setObj(){
        txtName = view.findViewById(R.id.txtNameProfile);
        imgPickProfile = view.findViewById(R.id.imgProfillePick);
    }
    private void setName(int id,UserDao userDao){
        user = (User) userDao.userAtualData(id);
        txtName.setText(user.getNome());
    }
    private void pickImage(){

    }

}