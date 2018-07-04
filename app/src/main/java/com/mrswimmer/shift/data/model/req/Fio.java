package com.mrswimmer.shift.data.model.req;

public class Fio {
    String first;
    String second;
    String third;
    String emailCo;
    String emailAcc;

    public Fio() {
    }

    public Fio(String first, String second, String third, String emailCo, String emailAcc) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.emailCo = emailCo;
        this.emailAcc = emailAcc;
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
