package com.example.courseapplication;

import static android.content.ContentValues.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String DBNAME="Login1.db";

    public DBHelper(Context context) {

        super(context,"Login1.db",null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, email TEXT,profession TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        // Sauvegarder les données existantes
        MyDB.execSQL("CREATE TABLE IF NOT EXISTS users_backup AS SELECT * FROM users");

        // Supprimer la table existante
        MyDB.execSQL("DROP TABLE IF EXISTS users");

        // Recréer la table
        onCreate(MyDB);

        // Restaurer les données sauvegardées dans la nouvelle table
        MyDB.execSQL("INSERT INTO users SELECT * FROM users_backup");

        // Supprimer la table de sauvegarde
        MyDB.execSQL("DROP TABLE IF EXISTS users_backup");
    }

    public boolean insertData(String username,String email,String profession,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("email",email);
        contentValues.put("Profession",profession);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password =?",new String[] {username,password});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

}
