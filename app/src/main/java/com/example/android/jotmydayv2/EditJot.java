package com.example.android.jotmydayv2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.android.jotmydayv2.Data.DatabaseHelper;

public class EditJot extends AppCompatActivity {

    private EditText jotEdit;
    private SQLiteDatabase jotDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jot);

        jotEdit = (EditText) findViewById(R.id.editJot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.editjot_actionbar, menu);
        return true;
    }





    private long addJot(String selectedDate, String jotEntry, Boolean isFav) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.JotEntry.COL_DATE, selectedDate);
        cv.put(DatabaseHelper.JotEntry.COL_ENTRY, jotEntry);
        cv.put(DatabaseHelper.JotEntry.COL_FAV, isFav);
        return jotDb.insert(DatabaseHelper.JotEntry.TABLE_NAME, null, cv);
    }






}


