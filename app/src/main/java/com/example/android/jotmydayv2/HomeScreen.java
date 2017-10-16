package com.example.android.jotmydayv2;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.android.jotmydayv2.Data.DatabaseHelper;

import java.util.Date;


public class HomeScreen extends AppCompatActivity {


    private TextView textJot;
    private SQLiteDatabase jotDb;
    private FloatingActionButton addJotFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.home_screen_toolbar);
        setSupportActionBar(myToolbar);
        addJotFab = (FloatingActionButton) findViewById(R.id.addJotButton);


        addJotFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this, EditJot.class);
                // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");

                startActivity(intent);
            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        jotDb = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_screen_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // search the database
                return true;

            case R.id.action_favourite:
                // favourited the jot
                return true;

            case R.id.action_settings:
                // settings menu
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


