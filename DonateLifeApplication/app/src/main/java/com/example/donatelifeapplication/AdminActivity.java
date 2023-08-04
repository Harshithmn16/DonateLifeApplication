package com.example.donatelifeapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.donatelifeapplication.R;

public class AdminActivity extends AppCompatActivity {

    Button admininsert,adminlist;
    DatabaseHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        admininsert = (Button)findViewById(R.id.btn_admin);
        adminlist = (Button)findViewById(R.id.btn_adminlist);
        DB = new DatabaseHelper(this);

        adminlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Address :"+res.getString(1)+"\n");
                    buffer.append("City :"+res.getString(2)+"\n");
                    buffer.append("State :"+res.getString(3)+"\n");
                    buffer.append("Phone :"+res.getString(4)+"\n");
                    buffer.append("Email :"+res.getString(5)+"\n");
                    buffer.append("Institution :"+res.getString(6)+"\n");
                    buffer.append("Age :"+res.getString(7)+"\n");
                    buffer.append("Gender :"+res.getString(8)+"\n");
                    buffer.append("Time :"+res.getString(9)+"\n");
                    buffer.append("Organ 1 :"+res.getString(10)+"\n");
                    buffer.append("Organ 2 :"+res.getString(11)+"\n");
                    buffer.append("Nominee Name :"+res.getString(12)+"\n");
                    buffer.append("Nominee Phone :"+res.getString(13)+"\n");
                    buffer.append("Nominee Email :"+res.getString(14)+"\n\n");
                    buffer.append("===================================");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });



        admininsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}