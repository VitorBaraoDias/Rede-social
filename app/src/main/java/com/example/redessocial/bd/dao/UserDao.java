package com.example.redessocial.bd.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.redessocial.bd.DbHelper;
import com.example.redessocial.objetos.Encrypt;
import com.example.redessocial.objetos.PerfilClass;
import com.example.redessocial.objetos.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper; // instância da classe que criamos

    public UserDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void inserirUser(User user){
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",user.getEmail());
        contentValues.put("pass",user.getPass());
        contentValues.put("name",user.getNome());
        db.insert("user", null, contentValues);
    }
    @SuppressLint("Range")
    public boolean validarExistencia(User user) {

        String strSql = "select * from user where email = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(user.getEmail())});

        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }
    @SuppressLint("Range")

    public boolean valiarUserName(User user) {

        String strSql = "select * from user where name = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(user.getNome())});

        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }
    @SuppressLint("Range")
    public boolean validarConta(User user) {

        String strSql = "select * from user where email = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(user.getEmail())});

        if (c.moveToFirst()) {
            //decrypte
            Encrypt encrypt = new Encrypt();
            String passEncryp = c.getString(c.getColumnIndex("pass"));
            String passBd = encrypt.caesarCipherDecrypte(passEncryp);
            System.out.println("aaaa"+passBd);
            System.out.println("aaaa"+user.getPass());

            //
            if(passBd.equals(user.getPass())){
                return true;
            }

        }
        return false;
    }
    @SuppressLint("Range")
    public int userAtual(User user){
        String strSql = "select * from user where email = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int id = 0;
        @SuppressLint("Recycle") Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(user.getEmail())});

        if (c.moveToFirst()) {
            id =c.getInt(c.getColumnIndex("id_user"));
            return id;
        }
       return id;
    }
    @SuppressLint("Range")
    public User userAtualData(int id){
        String strSql = "select * from user where id_user = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        User user;
        @SuppressLint("Recycle") Cursor c = db.rawQuery(strSql, new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            String email = c.getString(c.getColumnIndex("email"));
            String pass = c.getString(c.getColumnIndex("pass"));
            String name = c.getString(c.getColumnIndex("name"));
             user = new User(email,pass,name);
            return user;
        }
         return null;
    }
    @SuppressLint("Range")
    public List<PerfilClass> getUsers(String nometxt,int idUserAtual){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<PerfilClass> perfilList = new ArrayList<PerfilClass>();
        Cursor c =   db.rawQuery("select * from user where (name like '%"+nometxt+"%') and id_user != '"+idUserAtual+"'", null);
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                int id = c.getInt(c.getColumnIndex("id_user"));
                PerfilClass perfilClass = new PerfilClass(name,id);
                perfilList.add(perfilClass);
            } while (c.moveToNext());
        }
        return perfilList;
    }
}
