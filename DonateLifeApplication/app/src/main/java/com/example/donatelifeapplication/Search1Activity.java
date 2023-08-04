package com.example.donatelifeapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.donatelifeapplication.R;

public class Search1Activity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Boolean EditTextEmptyHold;
    EditText getNAME, getPhoneNumber,getOrgan;
    Button SubmitData, ShowData;
    String name, phoneNumber, query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        ShowData = (Button)findViewById(R.id.button2);
        ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search1Activity.this, SearchSQLiteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
    public void SQLiteTableBuild(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_Organ+" VARCHAR, "+SQLiteHelper.Table_Column_3_PhoneNumber+" VARCHAR)");
    }



}