package com.neerajjoshi.ppa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_PIN extends AppCompatActivity {

    Button submit;
    EditText op,np1,np2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);


        submit = findViewById(R.id.btn_submit);
        op= findViewById(R.id.et_oldpin);
        np1= findViewById(R.id.et_newpin1);
        np2= findViewById(R.id.et_newpin2);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(op.getText().toString().isEmpty() ||
                        np1.getText().toString().isEmpty() ||
                        np2.getText().toString().isEmpty() )
                {
                    Toast.makeText(Change_PIN.this,"All Fields are mendatery",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!np1.getText().toString().equals(np2.getText().toString()))
                {
                    Toast.makeText(Change_PIN.this,"Both new PIN should be same",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!op.getText().toString().equals(getPinFromSharedPreferences()))
                {
                    Toast.makeText(Change_PIN.this,"Old password did not match",Toast.LENGTH_SHORT).show();
                    return;

                }



                savePinInSharedPreferences(np1.getText().toString());
                Toast.makeText(Change_PIN.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();

                onBackPressed();




            }
        });
    }

    private String getPinFromSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("pinKey", "");
    }

    private void savePinInSharedPreferences(String pin)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pinKey", pin);
        editor.apply();
    }
}