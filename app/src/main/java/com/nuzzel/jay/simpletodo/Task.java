package com.nuzzel.jay.simpletodo;

/**
 * Created by Jay on 9/30/14.
 */
public class Task {
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

    // TODO convert task to writeable format for text file
    public String getWriteable() {
        return "";
    }
}
