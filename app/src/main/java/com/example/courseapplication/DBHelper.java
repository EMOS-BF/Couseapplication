package com.example.courseapplication;

import static android.content.ContentValues.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";

    public DBHelper(Context context) {

        super(context,"Login.db",null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE admins (username TEXT PRIMARY KEY, email TEXT, password TEXT)");
        MyDB.execSQL("create Table utilisateurs(username TEXT primary key, email TEXT,profession TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        // Sauvegarder les données existantes
        MyDB.execSQL("CREATE TABLE IF NOT EXISTS admins_backup AS SELECT * FROM admins");
        MyDB.execSQL("CREATE TABLE IF NOT EXISTS utilisateurs_backup AS SELECT * FROM utilisateurs");
        // Supprimer la table existante
        MyDB.execSQL("DROP TABLE IF EXISTS admins");
        MyDB.execSQL("DROP TABLE IF EXISTS utilisateurs");

        // Recréer la table
        onCreate(MyDB);

        // Restaurer les données sauvegardées dans la nouvelle table
        MyDB.execSQL("INSERT INTO admins SELECT * FROM admins_backup");
        MyDB.execSQL("INSERT INTO utilisateurs SELECT * FROM utilisateurs_backup");

        // Supprimer la table de sauvegarde
        MyDB.execSQL("DROP TABLE IF EXISTS admins_backup");
        MyDB.execSQL("DROP TABLE IF EXISTS utilisateurs_backup");
    }

    public boolean insertData(String username,String email,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDB.insert("admins",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }


    public boolean insertuserData(String username,String email,String profession,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("email",email);
        contentValues.put("Profession",profession);
        contentValues.put("password",password);
        long result = MyDB.insert("utilisateurs",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM utilisateurs", null);
        return data;
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admins where username = ?",new String[] {username});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admins where username = ? and password =?",new String[] {username,password});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

}
