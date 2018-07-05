package com.mrswimmer.shift.data.model.req;

public class Fio {
    String first;
    String second;
    String third;
    String emailCo;
    String emailAcc;
    int result;

    public Fio() {
    }

    public Fio(String first, String second, String third, String emailCo, String emailAcc, int result) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.emailCo = emailCo;
        this.emailAcc = emailAcc;
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    public String getEmailCo() {
        return emailCo;
    }

    public void setEmailCo(String emailCo) {
        this.emailCo = emailCo;
    }

    public String getEmailAcc() {
        return emailAcc;
    }

    public void setEmailAcc(String emailAcc) {
        this.emailAcc = emailAcc;
    }
}
