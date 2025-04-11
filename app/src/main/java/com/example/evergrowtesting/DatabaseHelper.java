package com.example.evergrowtesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TASK_TABLE = "task";
    public static final String COLUMN_TASKID = "taskId";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CHECKDONE = "isComplete";
    public static final String COLUMN_DATE = "deadline";
    public static final String COLUMN_GOALID = "goalId";

    public static final String GOAL_TABLE = "goal";
    public static final String COLUMN_GOAL_DESCRIPTION = "description";
    public static final String COLUMN_GOAL_CHECKDONE = "isComplete";
    public static final String COLUMN_GOAL_DATE = "deadline";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createGoalTable = "CREATE TABLE " + GOAL_TABLE + " (" +
                COLUMN_GOALID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GOAL_DESCRIPTION + " TEXT, " +
                COLUMN_GOAL_CHECKDONE + " BOOL, " +
                COLUMN_GOAL_DATE + " TEXT)";

        String createTaskTable = "CREATE TABLE " + TASK_TABLE + " (" +
                COLUMN_TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CHECKDONE + " BOOL, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_GOALID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_GOALID + ") REFERENCES " + GOAL_TABLE + "(" + COLUMN_GOALID + "))";

        db.execSQL(createGoalTable);
        db.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle schema changes if needed
    }

    public boolean addOneTask(TaskModel taskModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESCRIPTION, taskModel.getDescription());
        cv.put(COLUMN_DATE, taskModel.getDate());
        cv.put(COLUMN_CHECKDONE, taskModel.isCheckDone());
        cv.put(COLUMN_GOALID, taskModel.getGoalid());

        long insert = db.insert(TASK_TABLE, null, cv);
        return insert != -1;
    }

    public boolean addOneGoal(GoalModel goalModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GOAL_DESCRIPTION, goalModel.getDescription());
        cv.put(COLUMN_GOAL_CHECKDONE, goalModel.isCheckDone());
        cv.put(COLUMN_GOAL_DATE, goalModel.getDate());

        long insert = db.insert(GOAL_TABLE, null, cv);
        return insert != -1;
    }


    public ArrayList<GoalModel> getAllGoals() {
        ArrayList<GoalModel> goals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + GOAL_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GOALID));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GOAL_DESCRIPTION));
                boolean isComplete = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GOAL_CHECKDONE)) == 1;
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GOAL_DATE));

                goals.add(new GoalModel(id, desc, isComplete, date));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return goals;
    }

    public ArrayList<TaskModel> getTasksByGoalId(int goalId) {
        ArrayList<TaskModel> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TASK_TABLE + " WHERE " + COLUMN_GOALID + " = ?",
                new String[]{String.valueOf(goalId)}
        );

        if (cursor.moveToFirst()) {
            do {
                int taskId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASKID));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                boolean isComplete = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHECKDONE)) == 1;
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));

                tasks.add(new TaskModel(taskId, desc, isComplete, date, goalId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }



}
