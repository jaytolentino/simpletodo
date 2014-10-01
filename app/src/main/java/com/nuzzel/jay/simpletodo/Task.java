package com.nuzzel.jay.simpletodo;

import java.io.Serializable;

/**
 * Created by Jay on 9/30/14.
 */
public class Task implements Serializable {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        completed = false;
    }

    public void checkTask() {
        completed = !completed;
    }

    public boolean isComplete() {
       return completed;
    }

    public String getDescription() {
        return description;
    }

    public String getWritable() {
        String taskInfo = (completed == true ? "true" : "false");
        taskInfo += " " + description;
        return taskInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}