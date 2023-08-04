package com.example.donatelifeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatelifeapplication.R;

public class ResetActivity extends AppCompatActivity {
    TextView username;
    EditText pass,repass;
    Button confirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = (TextView)  findViewById(R.id.username_reset_text);
        pass = (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                String validpassword = "[a-zA-Z0-9._-]+[a-z]+\\.+[a-z]+";
                String validrepassword = "[a-zA-Z0-9._-]+[a-z]+\\.+[a-z]+";
                if(password.equals(repassword)) {


                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    } else
                    {

                        Toast.makeText(ResetActivity.this, "Password not updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(ResetActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}