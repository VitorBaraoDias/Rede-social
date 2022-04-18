package com.example.redessocial.bd.dao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redessocial.MainActivity;
import com.example.redessocial.bd.DbHelper;
import com.example.redessocial.objetos.DataMensagem;
import com.example.redessocial.objetos.DmLista;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MensagemDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper; // instância da classe que criamos

    public MensagemDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void inserirMensagem(DataMensagem dataMensagem){
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_mandou",dataMensagem.getIdMandou());
        contentValues.put("id_recebeu",dataMensagem.getIdRecebeu());
        contentValues.put("mensagem",dataMensagem.getMensagem());
        contentValues.put("datMensagem",dataMensagem.getHora());
        db.insert("mensagem", null, contentValues);
    }
    @SuppressLint("Range")
    public List<DataMensagem> getMensagens(int idMandou,int idRecebeu){
        String strSql = "select * from mensagem where id_mandou = ? and id_recebeu = ? or id_recebeu = ? and id_mandou = ?";
        List <DataMensagem>dataMensagemList = new ArrayList<DataMensagem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(idMandou),String.valueOf(idRecebeu),String.valueOf(idMandou),String.valueOf(idRecebeu)});
        if (c.moveToFirst()) {
            do {
                int idMandou1= c.getInt(c.getColumnIndex("id_mandou"));
                int idRecebeu1= c.getInt(c.getColumnIndex("id_recebeu"));
                String mensagem =c.getString(c.getColumnIndex("mensagem"));
                String dataMensagem = c.getString(c.getColumnIndex("datMensagem"));
                DataMensagem dataMensagem1 = new DataMensagem(idMandou1,idRecebeu1,mensagem,dataMensagem);
                dataMensagemList.add(dataMensagem1);
            } while (c.moveToNext());
        }
        return dataMensagemList;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("Range")
    public List<DmLista> getAbasConversas(int idUserAtual) {

        List<Integer> listaBd;
        listaBd = new ArrayList<>();

        List<Integer> listaOrdenada;
        listaOrdenada = new ArrayList<>();

        List<DmLista> dmListaList;
        dmListaList = new ArrayList<>();

        String strSql = "select DISTINCT id_recebeu from mensagem where id_mandou = ? "; //todos que o user mandou msg
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(idUserAtual)});
        if (c.moveToFirst()) {
            do {
                listaBd.add(c.getInt(c.getColumnIndex("id_recebeu")));

            } while (c.moveToNext());
        }
        strSql = "select DISTINCT id_mandou from mensagem where id_recebeu = ? "; //todos que o user recebeu msg
        db = dbHelper.getReadableDatabase();
        c = db.rawQuery(strSql, new String[]{String.valueOf(idUserAtual)});
        if (c.moveToFirst()) {
            do {
                listaBd.add(c.getInt(c.getColumnIndex("id_mandou")));
            } while (c.moveToNext());
        }

        System.out.println(listaOrdenada=listaBd.stream().distinct().collect(Collectors.toList()));

        for(int i = 0;i<listaOrdenada.size();i++){
            Cursor cursor2 = db.rawQuery("SELECT MAX(id_mensagem),mensagem from mensagem where id_mandou = ? and id_recebeu = ? or id_recebeu = ? and id_mandou = ? " , new String[]{String.valueOf(idUserAtual),String.valueOf(listaOrdenada.get(i)),String.valueOf(idUserAtual),String.valueOf(listaOrdenada.get(i))});

            if(cursor2.moveToFirst()){
                String mensagem = cursor2.getString(cursor2.getColumnIndex("mensagem"));

                Cursor cursorName = db.rawQuery("select name,id_user from user where id_user = ?", new String[]{String.valueOf(listaOrdenada.get(i))});
                if(cursorName.moveToFirst()){

                    String name = cursorName.getString(cursorName.getColumnIndex("name"));
                    int id = cursorName.getInt(cursorName.getColumnIndex("id_user"));
                    byte[] imagem = null;

                    String strSql2 = "select * from imgPerfil where id_user = ?";
                    Cursor c2 = db.rawQuery(strSql2, new String[]{String.valueOf(id)});
                    if (c2.moveToFirst()) {
                        imagem = c2.getBlob(c2.getColumnIndex("imagem"));
                    }

                    DmLista dmLista = new DmLista(name,mensagem,id,imagem);
                    dmListaList.add(dmLista);
                }
            }
        }
        return dmListaList;
    }
}
