package com.example.redessocial.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redessocial.R;
import com.example.redessocial.adapter.AdapterRecyclerMensagens;
import com.example.redessocial.bd.dao.ImgPerfilDao;
import com.example.redessocial.bd.dao.MensagemDao;
import com.example.redessocial.objetos.DataMensagem;
import com.example.redessocial.objetos.PerfilClass;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ActivityChat extends AppCompatActivity {
    //class
    PerfilClass perfilClass;
    //
    //textview
    private TextView txtPessoaConversando;
    //
    //imageview
    private ImageView imgClose,imgSendMensagem,imgPessoaConversando;
    //
    //EditText
    private EditText txtEscreverMensagem;
    //
    //Recycler
    private RecyclerView recyclerView;

    private List<DataMensagem> dataMensagemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle extras = getIntent().getExtras();
        perfilClass = (PerfilClass) extras.getSerializable("dataUser");
        int id = extras.getInt("meuId");


        setObj();
        setPessoaConversando();
        setMensagens(id,perfilClass.getId_profile());
        setAdapter(dataMensagemList,id);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgSendMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(testTxt()==true){
                    addMensagem(id,perfilClass.getId_profile());
                    setAdapter(dataMensagemList,id);
                    txtEscreverMensagem.setText("");
                }
            }
        });
        txtEscreverMensagem.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                recyclerView.smoothScrollToPosition(dataMensagemList.size());
                Toast.makeText(ActivityChat.this, "oi", Toast.LENGTH_SHORT).show();
// Aqui voc?? coloca o evento

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        Toast.makeText(this, String.valueOf("ID USER Pessoa conversando"+perfilClass.getId_profile()), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, String.valueOf("Meu id"+id), Toast.LENGTH_SHORT).show();
    }
    private void setObj(){
          txtPessoaConversando = findViewById(R.id.txtPessoaConversa);
          imgClose = findViewById(R.id.imgCloseConversa);
          imgSendMensagem = findViewById(R.id.imgSendMensagem);
          imgPessoaConversando = findViewById(R.id.imgPessoaConversando);
          txtEscreverMensagem = findViewById(R.id.txtEscreverMensagem);
          recyclerView = findViewById(R.id.recyclerMensagens);

    }
    private boolean testTxt(){
        if(txtEscreverMensagem.getText().equals("")){
            return false;
        }
        else {
            return true;
        }
    }
    private void addMensagem(int idMandou,int idRecebeu){
        String dataDaMensagem = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String msg = txtEscreverMensagem.getText().toString();
        //setClasse da mensagem
        DataMensagem dataMensagem = new DataMensagem(idMandou,idRecebeu,msg,dataDaMensagem);
        //
        //enviar bd
        MensagemDao mensagemDao = new MensagemDao(this);
        dataMensagemList.add(dataMensagem);
        mensagemDao.inserirMensagem(dataMensagem);
    }
    private void setMensagens(int idMandou,int idRecebeu) {
        MensagemDao mensagemDao = new MensagemDao(this);
        dataMensagemList = mensagemDao.getMensagens(idMandou,idRecebeu);
    }
    private void setAdapter(List<DataMensagem> dataMensagemList,int idUserAtual) {
        AdapterRecyclerMensagens adapterRecyclerConversa = new AdapterRecyclerMensagens(dataMensagemList,idUserAtual);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.smoothScrollToPosition(dataMensagemList.size());
        //VAI FAZER COM QUE APARE??A OS DADOS DA LISTA
        recyclerView.setAdapter(adapterRecyclerConversa);
    }
    private void setPessoaConversando(){
        ImgPerfilDao imgPerfilDao = new ImgPerfilDao(this);
        if(imgPerfilDao.verificarExistencia(perfilClass.getId_profile())){
            imgPessoaConversando.setImageBitmap(
                    Bitmap.createScaledBitmap(
                            BitmapFactory.decodeByteArray(imgPerfilDao.getImagem(perfilClass.getId_profile()), 0, imgPerfilDao.getImagem(perfilClass.getId_profile()).length),200, 200, false));
        }
        else{
            imgPessoaConversando.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        txtPessoaConversando.setText(perfilClass.getUser_name());


    }
}