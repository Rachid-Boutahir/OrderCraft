package com.simplon.artwood.beans;

import com.mysql.cj.util.DnsSrv;

import java.util.Objects;

public class Admin {
    private int adminId ;
    private String email;
    private String password;
    private String fullName;

    private String role;

    public Admin() {
    }

    public Admin( String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
    public Admin( int adminId, String email, String password, String fullName) {
        this();
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "Admin{" +
                    "adminId=" + adminId +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", fullName='" + fullName + '\'' +
                '}';
    }
}
