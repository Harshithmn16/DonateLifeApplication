package com.example.donatelifeapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="AdminInserted";
    public static final String TABLE_NAME="AdminInserted";
    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="Name";
    public static final String Table_Column_2_Organ="Organ";
    public static final String Table_Column_3_PhoneNumber="Phone";
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Organ+" VARCHAR, "+Table_Column_3_PhoneNumber+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

}