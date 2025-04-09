package com.example.evergrowtesting;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TASKID = "COLUMN_TASKID";
    public static final String COLUMN_DESCRIPTION1 = "COLUMN_DESCRIPTION";
    public static final String COLUMN_DESCRIPTION = COLUMN_DESCRIPTION1;
    public static final String COLUMN_CHECKDONE1 = "COLUMN_CHECKDONE";
    public static final String COLUMN_CHECKDONE = COLUMN_CHECKDONE1;
    public static final String COLUMN_DATE1 = "COLUMN_DATE";
    public static final String COLUMN_DATE = COLUMN_DATE1;
    public static final String GOAL_TABLE = "GOAL_TABLE";
    public static final String COLUMN_GOALID = "COLUMN_GOALID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Database.db", null, 1);
    }
    //Call the first time when Db is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
    String createtasktable = " CREATE TABLE " + TASK_TABLE + " (" + COLUMN_TASKID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CHECKDONE + " BOOL, " + COLUMN_DATE + " TEXT, " + COLUMN_GOALID +"INT)";

    String creategoaltable = " CREATE TABLE " + GOAL_TABLE + "( " + COLUMN_GOALID + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_DESCRIPTION1 + " TEXT, " + COLUMN_CHECKDONE1 + " BOOL, " + COLUMN_DATE1 + " TEXT)";


    db.execSQL(createtasktable);
    db.execSQL(creategoaltable);
    }


    //Update the db without recreating the whole database again when changing the db structure
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOneGoal(GoalModel goalModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESCRIPTION1, goalModel.getDescription());
        cv.put(COLUMN_CHECKDONE1, goalModel.isCheckDone());
        cv.put(COLUMN_DATE1, goalModel.getDate());

        db.insert(GOAL_TABLE,null,cv);

        return true;
    }

    public boolean addOneTask(TaskModel taskModel, GoalModel goalModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESCRIPTION, taskModel.getDescription());
        cv.put(COLUMN_CHECKDONE, taskModel.isCheckDone());
        cv.put(COLUMN_DATE, taskModel.getDate());
        cv.put(COLUMN_GOALID, goalModel.getGoalId());

        long insert = db.insert(TASK_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }else {
            return true;
        }

        //return true;
    }



}
