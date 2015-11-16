package com.yodle.testingdemocomplete.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private Year year;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, int age, Year year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.year = year;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public enum Year {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR;
    }
}
