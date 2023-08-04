package com.example.donatelifeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.donatelifeapplication.R;

public class HomeActivity extends AppCompatActivity {

    private Button button,btnsearch,go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button)findViewById(R.id.button_donate);
        btnsearch = (Button) findViewById(R.id.button_search) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Donate.class);
                startActivity(intent);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Search1Activity.class);
                startActivity(intent);
            }
        });

        go=(Button)findViewById(R.id.goback);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserLogin.class);
                startActivity(intent);
            }
        });


    }


}