package com.example.evergrowtesting;

import java.io.Serializable;

public class GoalModel implements Serializable {

    private int goalId;
    private String description;
    private boolean checkDone;
    private String date;
    private String name;

    // Note: find out how would the search function would show the goals and its related daily tasks

    // contructors for adding goals
    public GoalModel(int goalId, String name,  String description, boolean checkDone, String date) {
        this.goalId = goalId;
        this.description = description;
        this.checkDone = checkDone;
        this.date = date;
        this.name = name;
    }

    //Default constructor
    public GoalModel() {
    }

    // recycler testing purposes
    public GoalModel(String description) {
        this.description = description;
    }

    //ToString for testing
    @Override
    public String toString() {
        return "GoalModel{" +
                "goalId=" + goalId +
                ", description='" + description + '\'' +
                ", checkDone=" + checkDone +
                ", date='" + date + '\'' +
                '}';
    }

    public int getGoalId() {
        return goalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public boolean isCheckDone() {
        return checkDone;
    }

    public String getDate() {
        return date;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCheckDone(boolean checkDone) {
        this.checkDone = checkDone;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
