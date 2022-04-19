package com.example.redessocial.fragmentos;

import static android.app.Activity.RESULT_OK;

import android.content.ContentProvider;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redessocial.R;
import com.example.redessocial.bd.dao.ImgPerfilDao;
import com.example.redessocial.bd.dao.UserDao;
import com.example.redessocial.objetos.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Arrays;

public class FragPerfil extends Fragment {

    private View view;
    private TextView txtName;
    private ImageView imgPickProfile;
    private static int RESULT_LOAD_IMG = 1;
    String path="";
    String imgPath, fileName;
    int id_user;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_frag_profile, container, false);
        setObj();
        id_user = getArguments().getInt("id");
        UserDao userDao = new UserDao(getContext());


        setName(id_user,userDao);
        verificarImagem();
        imgPickProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               loadImagefromGallery();
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
    private void loadImagefromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            //  Block of code to try

            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Drawable d = new BitmapDrawable(getResources(), imgDecodableString);
                imgPickProfile.setBackground(d);
                imgPickProfile.setImageBitmap(null);

                Bitmap bitmap = ((BitmapDrawable) imgPickProfile.getBackground()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
                byte[] imagemByte = stream.toByteArray();
                //inserir ou alterar img bd
                inserirImagem(imagemByte);

            } else {
                Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e) {
            Toast.makeText(getContext(), "Error",Toast.LENGTH_LONG).show();
        }
    }
    private void verificarImagem(){
        ImgPerfilDao imgPerfilDao = new ImgPerfilDao(getContext());
        if(imgPerfilDao.verificarExistencia(id_user)==true){
            ByteArrayInputStream imageStream = new ByteArrayInputStream(imgPerfilDao.getImagem(id_user));
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imgPickProfile.setImageBitmap(
                    Bitmap.createScaledBitmap(
                            BitmapFactory.decodeByteArray(imgPerfilDao.getImagem(id_user), 0, imgPerfilDao.getImagem(id_user).length),220, 220, false));
                    imgPickProfile.setBackground(null);


        }
    }
    private void inserirImagem(byte[] imagem){
        ImgPerfilDao imgPerfilDao = new ImgPerfilDao(getContext());
        if(imgPerfilDao.verificarExistencia(id_user)==true){
            imgPerfilDao.alterarImagem(id_user,imagem);
        }
        else{
            imgPerfilDao.inserirImagem(id_user,imagem);
        }
    }
}