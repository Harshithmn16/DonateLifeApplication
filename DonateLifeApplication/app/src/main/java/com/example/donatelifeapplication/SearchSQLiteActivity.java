package com.example.donatelifeapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.donatelifeapplication.R;

import java.util.ArrayList;

public class SearchSQLiteActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> StudentList = new ArrayList<User>();
    ListAdapter listAdapter;
    SQLiteHelper sqLiteHelper;
    EditText editText;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sqlite);
        listView = (ListView) findViewById(R.id.listView1);
        editText = (EditText) findViewById(R.id.edittext1);
        listView.setTextFilterEnabled(true);
        sqLiteHelper = new SQLiteHelper(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User ListViewClickData = (User) parent.getItemAtPosition(position);
                Toast.makeText(SearchSQLiteActivity.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {
                listAdapter.getFilter().filter(stringVar.toString());
            }
        });
    }
    public void DisplayDataInToListView() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);
        User student;
        StudentList = new ArrayList<User>();
        if (cursor.moveToFirst()) {
            do {
                String tempName = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));
                String tempOrgan= cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Organ));
                String tempNumber= cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_PhoneNumber));
                student = new User(tempName, tempOrgan,tempNumber);
                StudentList.add(student);
            } while (cursor.moveToNext());
        }
        listAdapter = new ListAdapter(SearchSQLiteActivity.this, R.layout.custom_layout, StudentList);
        listView.setAdapter(listAdapter);
        cursor.close();
    }

    @Override
    protected void onResume() {
        DisplayDataInToListView() ;
        super.onResume();
    }

}