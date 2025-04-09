package com.example.evergrowtesting;

public class Task {
    String taskName;
    // Date deadline:
    String description;
    boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.description = "";
        this.isCompleted = false;
    }

    public Task(String taskName, String description, boolean isCompleted) {
        this.taskName = taskName;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
