package edu.miu.waa.onlineshopping.domain.model;

import edu.miu.waa.onlineshopping.domain.vo.Role;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private Integer userId;

    @Length(min = 5, message = "*Your user name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String userName;

    @Pattern(regexp = "((?=.*[A-Z]).{6,10})", message = "Password must have one upper case, one lower case and should be between 6 and 10 characters")
    private String password;

    @NotEmpty(message = "*Please provide your name")
    private String name;

    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    private Boolean active;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    private String phone;
    private String aboutUs;
    private Role role;

    @Valid
    Account account;

    private User(Integer userId, String userName, String password, String name, String lastName, Boolean active, String email, String phone, Role role, String aboutUs, Account account) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.aboutUs = aboutUs;
        this.account = account;
    }

    public static User of(Role role){
        return new User(null, null, null, null, null, true, null, null, role,null, null);
    }

    public static User of(Integer userId, String userName, String password, String name, String lastName, Boolean active, String email, String phone, Role role, String aboutUs, Account account) {
        return new User(userId, userName, password, name, lastName, active, email, phone, role, aboutUs, account);
    }
}
