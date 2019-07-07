package com.example.myapplication;

import java.util.Comparator;

public class foodInfo {
    String foodname,foodtaste,email,password;

    public foodInfo() {}

    public foodInfo(String foodname, String foodtaste, String email, String password) {
        this.foodname = foodname;
        this.foodtaste = foodtaste;
        this.email=email;
        this.password=password;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodtaste() {
        return foodtaste;
    }

    public void setFoodtaste(String foodtaste) {
        this.foodtaste = foodtaste;
    }

    public static Comparator<foodInfo> ByFoodName=new Comparator<foodInfo>() {
        @Override
        public int compare(foodInfo o1, foodInfo o2) {
            //Ascending
            return o1.getFoodname().compareToIgnoreCase(o2.getFoodname());
        }
    };

    public static Comparator<foodInfo> ByFoodTaste=new Comparator<foodInfo>() {
        @Override
        public int compare(foodInfo o1, foodInfo o2) {
            //Descending
            return o1.getFoodtaste().compareToIgnoreCase(o2.getFoodname());
        }
    };


}
