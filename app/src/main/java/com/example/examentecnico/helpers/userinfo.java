package com.example.examentecnico.helpers;

public class userinfo {

    public String user;
    public String country;
    public String state;
    public String gender;

    public userinfo(String user, String country, String state, String gender) {
        this.user = user;
        this.country = country;
        this.state = state;
        this.gender = gender;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
