package com.neerajjoshi.ppa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Card_view extends AppCompatActivity {
    TextView web_name;
    TextView pass_name;
    TextView username;
    int id;

    FloatingActionButton detete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        web_name = findViewById(R.id.web_name_tv);
        pass_name = findViewById(R.id.pass_tv);
        username = findViewById(R.id.username_tv);
        detete_button = findViewById(R.id.fab);



        web_name.setText(getIntent().getStringExtra("webname"));
        pass_name.setText(getIntent().getStringExtra("passwrd"));
        username.setText(getIntent().getStringExtra("username"));

        id = getIntent().getIntExtra("id",-1);

        detete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int res = deleteData(id);
                if( res == 1)
                {
                    Toast.makeText(Card_view.this,"Row "+id+" Deleted Successfully",Toast.LENGTH_SHORT).show();

                }else if(res == 0)
                    {
                        Toast.makeText(Card_view.this,"No row were deleted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Card_view.this,"Error!",Toast.LENGTH_SHORT).show();
                }

                onBackPressed();

            }
        });

    }

    private int deleteData(int id1)
    {
        int result = new DBmanager(getApplicationContext()).deleteData(id1);
        return  result;
    }
}