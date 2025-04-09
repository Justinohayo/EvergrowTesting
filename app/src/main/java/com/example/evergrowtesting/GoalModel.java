package com.example.evergrowtesting;

public class GoalModel {

    private int goalId;
    private String description;
    private boolean checkDone;
    private String date;

    // Note: find out how would the search function would show the goals and its related daily tasks

    // contructors for adding goals
    public GoalModel(int goalId, String description, boolean checkDone, String date) {
        this.goalId = goalId;
        this.description = description;
        this.checkDone = checkDone;
        this.date = date;
    }

    //Default constructor
    public GoalModel() {
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
