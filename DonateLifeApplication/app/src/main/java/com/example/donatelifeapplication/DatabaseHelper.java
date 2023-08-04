package com.example.donatelifeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetail(name TEXT , address TEXT , city TEXT, state TEXT, phone TEXT , email TEXT, institution TEXT , age TEXT , gender TEXT, time TEXT,organ1 TEXT,organ2 TEXT, nname TEXT, nphone TEXT, nemail TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetail");
    }

    public Boolean insertuserdata(String name, String address, String city,String state, String phone, String email,String institution, String age, String gender,String time,String organ1,String organ2, String nname, String nphone,String nemail)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("institution", institution);
        contentValues.put("age", age);
        contentValues.put("gender", gender);
        contentValues.put("time", time);
        contentValues.put("organ1", organ1);
        contentValues.put("organ2", organ2);
        contentValues.put("nname", nname);
        contentValues.put("nphone", nphone);
        contentValues.put("nemail", nemail);
        long result=DB.insert("UserDetail", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetail", null);
        return cursor;

    }
}