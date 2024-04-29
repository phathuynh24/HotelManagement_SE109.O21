package com.myproject.models;

import java.util.ArrayList;
import java.util.List;

public class Model_Account {

    private String userId;
    private String username;
    private String fullName;
    private String password;
    private List<String> permissions;
    
    public Model_Account(String userId, String username, String fullName, String password) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.permissions = new ArrayList<>();
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", fullName='" + fullName + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
