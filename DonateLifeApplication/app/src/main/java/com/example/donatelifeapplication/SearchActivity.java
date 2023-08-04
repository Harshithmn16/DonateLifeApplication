package com.example.donatelifeapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donatelifeapplication.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Boolean EditTextEmptyHold;
    EditText getNAME, getPhoneNumber,getOrgan;
    Button SubmitData, ShowData;
    String name, phoneNumber,organ,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SubmitData = (Button)findViewById(R.id.button);
        getPhoneNumber = (EditText)findViewById(R.id.editText3);
        ShowData = (Button)findViewById(R.id.button2);
        getNAME = (EditText)findViewById(R.id.editText);
        getOrgan=(EditText)findViewById(R.id.editText2);

        SubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
            }
        });

        ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchSQLiteActivity.class);
                startActivity(intent);
            }
        });
    }
    public void SQLiteDataBaseBuild(){
        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
    public void SQLiteTableBuild(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_Organ+" VARCHAR, "+SQLiteHelper.Table_Column_3_PhoneNumber+" VARCHAR);");
    }
    public void CheckEditTextStatus(){
        name = getNAME.getText().toString() ;
        organ = getOrgan.getText().toString();
        phoneNumber = getPhoneNumber.getText().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add("Kidney");list.add("kidney");
        list.add("Lung Portion");list.add("lung portion");list.add("Lung portion");list.add("lung Portion");
        list.add("Liver Portion");list.add("liver portion");list.add("Liver portion");list.add("liver Portion");
        list.add("Pancreas Portion");list.add("pancreas portion");list.add("Pancreas portion");list.add("pancreas Portion");
        list.add("Intestine Portion");list.add("intestine portion");list.add("Intestine portion");list.add("intestine Portion");
        list.add("Skin Tissue");list.add("skin tissue");list.add("Skin tissue");list.add("skin Tissue");
        list.add("Bone Tissue");list.add("bone tissue");list.add("Bone tissue");list.add("bone Tissue");
        list.add("Heart Valve Tissue");list.add("heart valve tissue");list.add("Heart valve tissue");list.add("Heart Valve tissue");list.add("heart Valve tissue");list.add("heart valve Tissue");list.add("heart Valve Tissue");list.add("Heart valve Tissue");
        list.add("Cornea tissue");list.add("cornea tissue");list.add("Cornea tissue");list.add("cornea Tissue");
        String validname = "^([A-Z][a-z]+)(s[A-Z][a-z]+)*$";

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(organ)|| TextUtils.isEmpty(phoneNumber)){
            EditTextEmptyHold = false ;
        }
        else if(!isValidPhone(phoneNumber)){
            Toast.makeText(SearchActivity.this,"Please enter a valid phone number!", Toast.LENGTH_LONG).show();
        }
        else if(!list.contains(organ)){
            Toast.makeText(SearchActivity.this,"Please enter a valid Organ! You may refer the list below!!", Toast.LENGTH_LONG).show();
        }
        else{
            EditTextEmptyHold = true ;
        }
    }
    public void InsertDataIntoSQLiteDatabase(){
        if(EditTextEmptyHold == true) {
            query = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (Name,Organ,Phone) VALUES('"+name+"', '"+organ+"', '"+phoneNumber+"');";
            sqLiteDatabase.execSQL(query);
            Toast.makeText(SearchActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SearchActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }
    public void EmptyEditTextAfterDataInsert(){
        getNAME.getText().clear();
        getOrgan.getText().clear();
        getPhoneNumber.getText().clear();
    }

    public static boolean isValidPhone(String phone) {
        String expression = "^[1-9]{1}[0-9]{9}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        }
        else{
            return false;
        }
    }

}