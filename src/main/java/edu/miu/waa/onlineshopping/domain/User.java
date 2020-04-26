package edu.miu.waa.onlineshopping.domain;

import lombok.Getter;

@Getter
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private Boolean active;
    private String email;
    private String phone;
    private Role role;

    private User(Integer userId, String userName, String password, String name, String lastName, Boolean active, String email, String phone, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public static User of(Integer userId, String userName, String password, String name, String lastName, Boolean active, String email, String phone, Role role) {
        return new User(userId, userName, password, name, lastName, active, email, phone, role);
    }
}
