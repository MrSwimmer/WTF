package com.mrswimmer.shift.data.model.firebase;

public class Task {
    String id;
    int count;
    String first;
    String second;
    String third;
    String country;

    public Task(String id, int count, String first, String second, String third, String country) {
        this.id = id;
        this.count = count;
        this.first = first;
        this.second = second;
        this.third = third;
        this.country = country;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
