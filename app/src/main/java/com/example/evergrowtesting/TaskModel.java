package com.example.evergrowtesting;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TaskModel implements Serializable {

    private int taskid;
    private String Description;
    private boolean checkDone;
    private String date;
    private int goalid;

//Note: Figure out how the foreign would communicate between class
    // Check how date String would work in the database
    // Foreign Key does not have a setter, how does it effect the database?

    //contructors for addint daily tasks to the long term goal db
    public TaskModel(int taskid, String description, boolean checkDone, String date, int goalid) {
        this.taskid = taskid;
        this.Description = description;
        this.checkDone = checkDone;
        this.date = date;
        this.goalid = goalid;
    }

    // This constructor is for adding daily task to the database
    public TaskModel(String date, boolean checkDone, String description, int taskid) {
        this.date = date;
        this.checkDone = checkDone;
        Description = description;
        this.taskid = taskid;
    }

    //default constructor
    public TaskModel()
    {

    }

    public TaskModel(String description){
        this.Description = description;
    }

    //ToString for testing
    @Override
    public String toString() {
        return "TaskModel{" +
                "taskid=" + taskid +
                ", Description='" + Description + '\'' +
                ", checkDone=" + checkDone +
                ", date='" + date + '\'' +
                ", goalid=" + goalid +
                '}';
    }

    public int getTaskid() {
        return taskid;
    }

    public String getDescription() {
        return Description;
    }

    public boolean isCheckDone() {
        return checkDone;
    }

    public String getDate() {
        return date;
    }

    public int getGoalid() {
        return goalid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCheckDone(boolean checkDone) {
        this.checkDone = checkDone;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
