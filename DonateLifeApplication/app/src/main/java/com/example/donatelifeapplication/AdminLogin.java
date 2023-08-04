package com.example.donatelifeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donatelifeapplication.R;

public class AdminLogin extends AppCompatActivity {

    EditText admin, password;
    Button adminlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        String admin1 = "admin1";
        String admin2 = "admin2";
        String admin3 = "admin3";
        String password1 = "abc";
        String password2 = "123";
        String password3 = "abc123";

        admin = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        adminlogin = (Button) findViewById(R.id.btnsignin1);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String adm = admin.getText().toString();
                String pass = password.getText().toString();

                if(adm.equals("")||pass.equals(""))
                    Toast.makeText(AdminLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(adm.equals(admin1)||adm.equals(admin2)||adm.equals(admin3)){
                        if(pass.equals(password1)||pass.equals(password2)||pass.equals(password3)) {
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                            Toast.makeText(AdminLogin.this, "Admin sign in successfull!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminLogin.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}