package com.neerajjoshi.ppa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ResAdapter resAdapter;

    private ArrayList<WebInstance> wepassList;

    private DrawerLayout drawerLayout ;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView nav;
    Toolbar toolbar;



    @Override
    protected void onResume() {
        refreshRecyclerview();
        super.onResume();
    }

    @Override
    protected void onPause() {
        //onBackPressed();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nav = findViewById(R.id.nav_drawer);
        drawerLayout =findViewById(R.id.drawerLayout);
        recyclerView = findViewById(R.id.recycle_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        wepassList = new ArrayList<>();

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.menu_home){
                    Toast.makeText(getApplicationContext(),"Home Option Selected",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                if(id == R.id.menu_setting){
                    Toast.makeText(getApplicationContext(),"Setting Option Selected",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(id == R.id.menu_setpin){
                    Toast.makeText(getApplicationContext(),"Setting Option Selected",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(MainActivity.this,Change_PIN.class));
                }
                if(id == R.id.menu_about_us){
                    Toast.makeText(getApplicationContext(),"About us Option Selected",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

    }


    private void refreshRecyclerview()
    {
        Cursor cursor = new DBmanager(this).readData();
        wepassList.clear();
        while (cursor.moveToNext())
        {
            wepassList.add(new WebInstance(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(0)));
        }

        resAdapter = new ResAdapter(wepassList);
        recyclerView.setAdapter(resAdapter);

    }


}