package com.duarte.taskmanager;

public class Task {

    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        return (done ? "[x] " : "[ ] ") + description;
    }
}