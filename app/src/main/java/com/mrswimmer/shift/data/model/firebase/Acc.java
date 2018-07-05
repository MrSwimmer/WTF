package com.mrswimmer.shift.data.model.firebase;

public class Acc {
    String email;
    String country;
    String id;

    public Acc(String email, String country, String id) {
        this.email = email;
        this.country = country;
        this.id = id;
    }

    public Acc() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
