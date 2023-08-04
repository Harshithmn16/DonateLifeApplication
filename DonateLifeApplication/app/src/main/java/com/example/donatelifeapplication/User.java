package com.example.donatelifeapplication;

public class User {
    String name = null;
    String number = null;
    String organ=null;

    public String getOrgan() {
        return organ;
    }
    public void setOrgan(String organ2) {
        this.organ = organ2;
    }
    public User(String Sname, String Sorgan, String Snumber) {
        super();
        this.name = Sname;
        this.organ = Sorgan;
        this.number=Snumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String Name2) {
        this.name = Name2;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number2) {
        this.number = number2;
    }
    public String toString() {
        return name + " " +organ + " " +number;

    }
}