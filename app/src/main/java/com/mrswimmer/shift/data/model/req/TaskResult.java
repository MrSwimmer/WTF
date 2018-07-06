package com.mrswimmer.shift.data.model.req;

public class TaskResult {
    String id;
    int result;

    public TaskResult() {
    }

    public TaskResult(String id, int result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
