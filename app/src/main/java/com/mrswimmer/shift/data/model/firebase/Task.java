package com.mrswimmer.shift.data.model.firebase;

public class Task {
    String id;
    int count;

    public Task(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
