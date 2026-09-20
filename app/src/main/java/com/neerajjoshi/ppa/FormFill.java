package com.neerajjoshi.ppa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormFill extends AppCompatActivity {

    EditText website;
    EditText user_name;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fill);


        website = findViewById(R.id.et_website);
        user_name = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        submit =findViewById(R.id.btn_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(website.getText().toString(),user_name.getText().toString(),password.getText().toString());
            }
        });
    }

    private void insertData(String web, String umane, String pass) {

        if(isFormEmpty(web,umane,pass))
        {
            Toast.makeText(this,"Enter Something to Submit",Toast.LENGTH_SHORT).show();
            return;
        }


        String result = new DBmanager(this).addRecord(web,umane,pass);

        website.getText().clear();
        user_name.getText().clear();
        password.getText().clear();

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    private boolean isFormEmpty(String web, String umane, String pass)
    {
        if(web.isEmpty() && umane.isEmpty() && pass.isEmpty())
        {
            return true;
        }else {
            return false;
        }
    }

}