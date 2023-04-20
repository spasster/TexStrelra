package com.example.texstrelra.models;

public class UserInfo {
    private final int user_id;
    private final String login;
    private final String password;
    private final int gb_count;
    private final int points_count;
    private final String number;
    private final int age;

    public UserInfo(int user_id, String login, String password, int gb_count, int points_count, String number, int age) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.gb_count = gb_count;
        this.points_count = points_count;
        this.number = number;
        this.age = age;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getGb_count() {
        return gb_count;
    }

    public int getPoints_count() {
        return points_count;
    }

    public String getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                "password='" + password + '\'' +
                "gb_count='" + gb_count + '\'' +
                "points_count='" + points_count + '\'' +
                "number='" + number + '\'' +
                "age='" + age + '\'' +
                '}';
    }
}