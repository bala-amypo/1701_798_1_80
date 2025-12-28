package com.example.demo.dto;

public class RegisterRequest {

    private String fullName;
    private String email;
    private String password;
    private String role;
    private String department;

    public RegisterRequest() {}

    public RegisterRequest(String fullName, String email,
                           String password, String role,
                           String department) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }
}
