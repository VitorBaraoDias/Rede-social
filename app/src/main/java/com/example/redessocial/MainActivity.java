package com.example.redessocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redessocial.bd.DbHelper;
import com.example.redessocial.bd.dao.UserDao;
import com.example.redessocial.objetos.Encrypt;
import com.example.redessocial.objetos.ToastCustom;
import com.example.redessocial.objetos.User;

public class MainActivity extends AppCompatActivity {
    //TEXTVIEW
    private TextView txtCriarConta,txtOpcaoLogar,txtWelcome,txtOpcaoConta;
    //EDIT TEXT
    private EditText txtEmail,txtPass;
    //IMAGE VIEW
    private ImageView btnLogar,imgVisibility;

    Boolean bTesteOpacaoLogin = false,testVisibilty = false;
    ToastCustom toastCustom = new ToastCustom();
    //
    String AES = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        UserDao userDao = new UserDao(this);

        setObs();

        txtCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              opcaoCrearOrLogin();
            }

        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!testBoxes()){
                    testLogin(userDao);
                }
            }
        });
        imgVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              setVisibilityPass();
            }
        });
    }
    private void setObs(){
        //TEXTVIEW
        txtCriarConta = findViewById(R.id.txtCriarConta);
        txtOpcaoLogar = findViewById(R.id.txtOpcaoLogar);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtOpcaoConta = findViewById(R.id.txtOpcaoConta);
        //EDIT TEXT
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        //IMAGE VIEW
        btnLogar = findViewById(R.id.btnLogar);
        imgVisibility = findViewById(R.id.imgVisibilityPass);
    }
    private boolean testBoxes(){
        if (txtEmail.getText().toString().equals("")){
            txtEmail.setError("Empty!");
            txtEmail.requestFocus();
            return true;
        }
        else if (txtPass.getText().toString().equals("")){
            txtPass.setError("Empty!");
            txtPass.requestFocus();
            return true;

        }
        return false;
    }
    private void testLogin(UserDao userDao){
        if(bTesteOpacaoLogin){
            String email = txtEmail.getText().toString().trim();
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                txtEmail.setError("Enter Valid Email Address");
                txtEmail.requestFocus();
                txtEmail.setText("");
            }
            else if(txtPass.getText().length()<8){
                txtPass.setError("Minimum 8 digits");
                txtPass.requestFocus();
                txtPass.setText("");
            }
            else {
                createAccount(userDao);
                clearBox();
            }
        }
        else{
           testSignUp(userDao);
        }
    }
    private void clearBox(){
        txtEmail.setText("");
        txtPass.setText("");
    }
    private void setVisibilityPass(){
        if(testVisibilty==false){
            txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
            txtPass.setSelection(txtPass.getText().length());
            testVisibilty = true;
        }
        else{
            txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            txtPass.setSelection(txtPass.getText().length());
            testVisibilty = false;
        }
    }
    private void opcaoCrearOrLogin(){
        if(!bTesteOpacaoLogin){
            txtOpcaoLogar.setText("Sign up");
            txtWelcome.setText("Create");
            txtOpcaoConta.setText("Already have an account?");
            txtCriarConta.setText("Login here");
            bTesteOpacaoLogin=true;
            clearBox();
        }
        else{
            txtOpcaoLogar.setText("Sign in");
            txtWelcome.setText("Skygram");
            txtOpcaoConta.setText("Don't have a account?");
            txtCriarConta.setText("Create");
            bTesteOpacaoLogin=false;
            clearBox();
        }
        txtEmail.requestFocus();
    }
    private void testSignUp(UserDao userDao){
        String emailInserido = txtEmail.getText().toString();
        String pass = txtPass.getText().toString().trim();
        User user = new User(emailInserido,pass);
        if(userDao.validarConta(user)){
            Intent intent = new Intent(this,ActivityMenu.class);
            int id = userDao.userAtual(user);
            intent.putExtra("id",id);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            clearBox();
        }
        else {
            toastCustom.show(getApplicationContext(),"Does not exist",true);
            clearBox();
            txtEmail.requestFocus();
        }
    }
    private void createAccount(UserDao userDao){
        //set variable
        String emailInserido = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();
        //
        //encrypt
        Encrypt encrypt = new Encrypt();
        String encrypPass = encrypt.caesarCipherEncrypt(pass);
        //
        //send data to bd
        User user = new User(emailInserido,encrypPass);
        if(!userDao.validarExistencia(user)){

            Intent intent = new Intent(this, ActivityInsertName.class);
            intent.putExtra("dataCreate",user);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            clearBox();
        }
        else{
            toastCustom.show(getApplicationContext(),"An account with this E-mail already exists",true);
            txtEmail.requestFocus();
        }
        //
        //SUCESS
    }
}