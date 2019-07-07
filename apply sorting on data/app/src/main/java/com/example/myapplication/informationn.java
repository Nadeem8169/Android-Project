package com.example.myapplication;

public class informationn {

    public String fullname,username,email,password,type;

    public informationn(){

    }


    public informationn(String fullname, String username, String email, String password, String type) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
}
