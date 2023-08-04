package com.example.donatelifeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donatelifeapplication.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Donate extends AppCompatActivity {
    EditText name,address,city,state,phone,email,institution,age,gender,time,organ1,organ2,nname,nphone,nemail;
    Button insert;
    DatabaseHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        name =(EditText)findViewById(R.id.ename);
        address=(EditText)findViewById(R.id.eaddress);
        city=(EditText)findViewById(R.id.ecity);
        state=(EditText)findViewById(R.id.estate);
        phone=(EditText)findViewById(R.id.ephone);
        email=(EditText)findViewById(R.id.eemail);
        institution=(EditText)findViewById(R.id.einstitution);
        age=(EditText)findViewById(R.id.eage);
        gender=(EditText)findViewById(R.id.egender);
        time=(EditText)findViewById(R.id.etime);
        organ1=(EditText)findViewById(R.id.eorgan1);
        organ2=(EditText)findViewById(R.id.eorgan2);
        nname=(EditText)findViewById(R.id.enname);
        nphone=(EditText)findViewById(R.id.enphone);
        nemail=(EditText)findViewById(R.id.enemail);
        insert=(Button) findViewById(R.id.button_add);


        DB = new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String cityTXT = city.getText().toString();
                String stateTXT = state.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();
                String institutionTXT = institution.getText().toString();
                String ageTXT = age.getText().toString();
                String genderTXT = gender.getText().toString();
                String timeTXT = time.getText().toString();
                String organ1TXT = organ1.getText().toString();
                String organ2TXT = organ2.getText().toString();
                String nnameTXT = nname.getText().toString();
                String nphoneTXT = nphone.getText().toString();
                String nemailTXT = nemail.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String validname = "^([A-Z][a-z]+)(s[A-Z][a-z]+)*$";
                String validgender ="^([M|m]ale|[F|f]emale|[o|O]thers|[o|O]ther)";
                String validage = "^[1-9]?[0-9]{1}$|^100";

                ArrayList<String> listorgan = new ArrayList<>();
                listorgan.add("None");listorgan.add("none");
                listorgan.add("kidney");listorgan.add("Kidney");
                listorgan.add("Lung Portion");listorgan.add("lung portion");listorgan.add("Lung portion");listorgan.add("lung Portion");
                listorgan.add("Liver Portion");listorgan.add("liver portion");listorgan.add("Liver portion");listorgan.add("liver Portion");
                listorgan.add("Pancreas Portion");listorgan.add("pancreas portion");listorgan.add("Pancreas portion");listorgan.add("pancreas Portion");
                listorgan.add("Intestine Portion");listorgan.add("intestine portion");listorgan.add("Intestine portion");listorgan.add("intestine Portion");
                listorgan.add("Skin Tissue");listorgan.add("skin tissue");listorgan.add("Skin tissue");listorgan.add("skin Tissue");
                listorgan.add("Bone Tissue");listorgan.add("bone tissue");listorgan.add("Bone tissue");listorgan.add("bone Tissue");
                listorgan.add("Heart Valve Tissue");listorgan.add("heart valve tissue");listorgan.add("Heart valve tissue");listorgan.add("Heart Valve tissue");listorgan.add("heart Valve tissue");listorgan.add("heart valve Tissue");listorgan.add("heart Valve Tissue");listorgan.add("Heart valve Tissue");
                listorgan.add("Cornea tissue");listorgan.add("cornea tissue");listorgan.add("Cornea tissue");listorgan.add("cornea Tissue");

                ArrayList<String> list1 = new ArrayList<>();
                list1.add("Living Donar");list1.add("living Donar");list1.add("Living donar");list1.add("living donar");
                list1.add("Deceased Donar");list1.add("deceased Donar");list1.add("Deceased donar");list1.add("deceased donar");
                list1.add("After Death");list1.add("after Death");list1.add("After death");list1.add("after death");

                ArrayList<String> list2 = new ArrayList<>();
                list2.add("National Organ Organisation");list2.add("national organ organisation");list2.add("National Organ organisation");list2.add("National organ organisation");list2.add("national Organ organisation");list2.add("national Organ Organisation");list2.add("National organ Organisation");list2.add("national organ Organisation");
                list2.add("National Biometric Center");list2.add("national biometric center");list2.add("National Biometric center");list2.add("National biometric center");list2.add("national Biometric center");list2.add("national Biometric Center");list2.add("National biometric Center");list2.add("national biometric Center");
                list2.add("Indian Govt Organ Transplant Center");list2.add("indian govt Organ Transplant Center");list2.add("Indian govt Organ Transplant Center");list2.add("indian Govt Organ Transplant Center");
                list2.add("indian govt organ transplant center");list2.add("Indian govt organ transplant center");

                //Boolean checkinsertdata = DB.insertuserdata(nameTXT,addressTXT,cityTXT,stateTXT,phoneTXT,emailTXT,institutionTXT,ageTXT,genderTXT,timeTXT,organ1TXT,organ2TXT,nnameTXT,nphoneTXT,nemailTXT);
                if (nameTXT.isEmpty()||addressTXT.isEmpty()||cityTXT.isEmpty()||stateTXT.isEmpty()||phoneTXT.isEmpty()||emailTXT.isEmpty()||institutionTXT.isEmpty()||ageTXT.isEmpty()||genderTXT.isEmpty()||timeTXT.isEmpty()||nnameTXT.isEmpty()||nphoneTXT.isEmpty()||nemailTXT.isEmpty()){
                    Toast.makeText(Donate.this, "Please enter all the details!", Toast.LENGTH_LONG).show();
                }
                else if(!isValidPhone(phoneTXT)){
                    Toast.makeText(view.getContext(), "Please enter your valid phone number!", Toast.LENGTH_LONG).show();
                }
                else if(!isValidPhone(nphoneTXT)){
                    Toast.makeText(view.getContext(), "Please enter a valid nominee phone number!", Toast.LENGTH_LONG).show();
                }
                else if(!emailTXT.trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"Please enter your valid email address!",Toast.LENGTH_LONG).show();
                }
                else if(!nemailTXT.trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid nominee email address!",Toast.LENGTH_LONG).show();
                }
                else if(!nameTXT.trim().matches(validname)) {
                    Toast.makeText(getApplicationContext(),"Please enter your valid name!",Toast.LENGTH_LONG).show();
                }
                else if(!nnameTXT.trim().matches(validname)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid nominee name!",Toast.LENGTH_LONG).show();
                }
                else if(!ageTXT.trim().matches(validage)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid age!",Toast.LENGTH_LONG).show();
                }
                else if(!genderTXT.trim().matches(validgender)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid gender!",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Or else enter others!",Toast.LENGTH_LONG).show();
                }
                else if(!listorgan.contains(organ1TXT)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid organ! You may refer the list given!",Toast.LENGTH_LONG).show();
                }
                else if(!listorgan.contains(organ2TXT)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid organ! You may refer the list given!",Toast.LENGTH_LONG).show();
                }
                else if(!list2.contains(institutionTXT)) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid institution name! You may refer the above list!",Toast.LENGTH_LONG).show();
                }
                else if(organ1TXT.equals(organ2TXT)) {
                    Toast.makeText(getApplicationContext(),"Organ already entered! You may refer the list given!",Toast.LENGTH_LONG).show();
                }
                else if(!list1.contains(timeTXT)) {
                    Toast.makeText(getApplicationContext(),"Please enter valid TIME OF DONATION! Refer the above details.",Toast.LENGTH_LONG).show();
                }
                else if(nameTXT.equals(nnameTXT)) {
                    Toast.makeText(getApplicationContext(),"This name is already entered!",Toast.LENGTH_LONG).show();
                }
                else if(phoneTXT.equals(nphoneTXT)) {
                    Toast.makeText(getApplicationContext(),"This phone number is already entered!",Toast.LENGTH_LONG).show();
                }
                else if(emailTXT.equals(nemailTXT)) {
                    Toast.makeText(getApplicationContext(),"This email ID is already entered!",Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(Donate.this, "Your application is submitted successflly!", Toast.LENGTH_LONG).show();
                    Boolean checkinsertdata = DB.insertuserdata(nameTXT,addressTXT,cityTXT,stateTXT,phoneTXT,emailTXT,institutionTXT,ageTXT,genderTXT,timeTXT,organ1TXT,organ2TXT,nnameTXT,nphoneTXT,nemailTXT);
                    Intent intent = new Intent(getApplicationContext(), SubmissionSuccess.class);
                    startActivity(intent);
                }
            }        });

    }

    public static boolean isValidPhone(String phone)
    {
        String expression = "^[1-9]{1}[0-9]{9}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}