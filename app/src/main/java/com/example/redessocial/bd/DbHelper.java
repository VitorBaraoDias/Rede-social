package com.example.redessocial.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bd_skygram";
    private static final int DATABASE_VERSION = 1;

    public static final String tableUser = "create table user"
            + "(id_user integer primary key autoincrement,"
            + " email text not null,"
            + " pass text not null,"
            + " name text not null)";

    public static final String tableMensagem = "create table mensagem"
            + "(id_mensagem integer primary key autoincrement,"
            + " id_mandou intenger not null,"
            + " id_recebeu intenger not null,"
            + " mensagem text not null,"
            + " datMensagem text not null)";

    public static final String tableImgPerfil= "create table imgPerfil"
            + "(id_imgPeril integer primary key autoincrement,"
            + " id_user intenger not null,"
            + " imagem blob not null)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUser);
        db.execSQL(tableMensagem);
        db.execSQL(tableImgPerfil);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
