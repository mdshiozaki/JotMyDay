package com.example.android.jotmydayv2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.Date;

import static com.example.android.jotmydayv2.Data.DatabaseHelper.JotEntry.TABLE_NAME;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jots.db";
    private static final int DATABASE_VERSION = 1;




    public static final class JotEntry implements BaseColumns {
        public static final String TABLE_NAME = "jots";
        public static final String COL_DATE = "date";
        public static final String COL_ENTRY = "jotentry";
        public static final String COL_FAV = "favourite";
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_DB = "CREATE TABLE " + TABLE_NAME + "(" +
                JotEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JotEntry.COL_DATE + " TEXT NOT NULL, " +
                JotEntry.COL_ENTRY + " TEXT NOT NULL, " +
                JotEntry.COL_FAV + " BOOLEAN " +
                ");";
        db.execSQL(SQL_CREATE_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addJot(Date selectedDate, String jotEntry, boolean isFavourite) {
        ContentValues values = new ContentValues();
        values.put(JotEntry.COL_DATE, selectedDate.toString());
        values.put(JotEntry.COL_ENTRY, jotEntry);
        values.put(JotEntry.COL_FAV, isFavourite);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(JotEntry.TABLE_NAME, null, values);
        db.close();

    }

    public void deleteJot(SQLiteDatabase db, Date jotDate) {
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + JotEntry.COL_DATE + "=\"" + jotDate.toString() + "\";");
    }


    public void favJot(SQLiteDatabase db, Date jotDate) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + JotEntry.COL_DATE + "=\"" + jotDate.toString() + "\";";
        ContentValues contentValues = new ContentValues();
        contentValues.put(JotEntry.COL_FAV, true);
        String[] args = {jotDate.toString() };
        db.update(TABLE_NAME, contentValues, JotEntry.COL_DATE + "=?",args);
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        db.close();
        return dbString;
    }

}
