package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "SHOPPING_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ACTIVE")
    private Boolean active;

    private String email;

    @Column(name = "ABOUT_Us")
    private String aboutUs;

    private String phone;

    private String role;

    public UserEntity() {
    }

    public UserEntity(String userName, String password, String email, String phone, String role, String name, String lastName, String aboutUs) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.active = false;
        this.aboutUs = aboutUs;
    }
}
