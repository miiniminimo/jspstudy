package com.study.jspstudy.model;

import java.sql.Timestamp;

public class User {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String schoolName;
    private String grade;
    private String email;
    private boolean admin;
    private Timestamp createdAt;

    public User() {}

    public User(String userId, String password, String name,
                String schoolName, String grade, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.schoolName = schoolName;
        this.grade = grade;
        this.email = email;
        this.admin = false;
    }

    // getter / setter들

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
