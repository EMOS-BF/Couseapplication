package com.example.courseapplication;

public class User {
    private String username;
    private String email;
    private String profession;

    public User(String username, String email, String profession) {
        this.username = username;
        this.email = email;
        this.profession = profession;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }
}

