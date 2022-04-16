package com.example.redessocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.redessocial.ActivityMenu;
import com.example.redessocial.R;
import com.example.redessocial.bd.dao.UserDao;
import com.example.redessocial.objetos.ToastCustom;
import com.example.redessocial.objetos.User;

import java.io.Serializable;

public class ActivityInsertName extends AppCompatActivity {
    User user;

    private EditText txtName;
    private Button btnInserir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_name);
        //SET BUNDLE EXTRA CLASS
        Bundle extras = getIntent().getExtras();
        user = (User) extras.getSerializable("dataCreate");
        //
        //DAO
        UserDao userDao = new UserDao(this);
        ToastCustom toastCustom = new ToastCustom();
        //
        setoObj();
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( txtName.getText().toString().equals("") ){
                    txtName.setError("Invalid");
                }
               else{
                   //false not exist name
                   testUserName(userDao,toastCustom);
               }
            }
        });

    }
    private void setoObj(){
        txtName = findViewById(R.id.txtNameUser);
        btnInserir = findViewById(R.id.btnInsertNamePerson);
    }
    private void testUserName(UserDao userDao,ToastCustom toastCustom){
        user.setNome(txtName.getText().toString());
        if(!userDao.valiarUserName(user)){
            userDao.inserirUser(user);
            int id = userDao.userAtual(user);
            toastCustom.show(getApplicationContext(),"Welcome",true);
            Intent intent = new Intent(this, ActivityMenu.class);
            intent.putExtra("id",id);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
        else{
            txtName.setError("this username already exists");
            txtName.setText("");
        }
    }
}