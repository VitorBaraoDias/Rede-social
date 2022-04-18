package com.example.redessocial.bd.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.redessocial.bd.DbHelper;

public class ImgPerfilDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper; // instância da classe que criamos

    public ImgPerfilDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void inserirImagem(int id,byte[] imagem){
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_user",id);
        contentValues.put("imagem",imagem);
        db.insert("imgPerfil", null, contentValues);
    }
    public void alterarImagem(int id,byte[] imagem){
        ContentValues contentValues=new ContentValues();
        contentValues.put("imagem",imagem);
        db.update("imgPerfil", contentValues,"id_user=" + id ,null);
    }
    public boolean verificarExistencia(int id){

        String strSql = "select * from imgPerfil where id_user = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }
    @SuppressLint("Range")
    public byte[] getImagem(int id){
        String strSql = "select * from imgPerfil where id_user = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        byte[] imagem = null;
        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            imagem = c.getBlob(c.getColumnIndex("imagem"));
        }
        return imagem;
    }
}
