package com.example.evergrowtesting;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TASKID = "COLUMN_TASKID";
    public static final String COLUMN_DESCRIPTION = "COLUMN_DESCRIPTION";
    public static final String COLUMN_CHECKDONE = "COLUMN_CHECKDONE";
    public static final String COLUMN_DATE = "COLUMN_DATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Database.db", null, 1);
    }

    //Call the first time when Db is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
    String createtablestatement = " CREATE TABLE " + TASK_TABLE + " (" + COLUMN_TASKID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CHECKDONE + " BOOL, " + COLUMN_DATE + " TEXT)";


    db.execSQL(createtablestatement);
    }


    //Update the db without recreating the whole database again when changing the db structure
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
