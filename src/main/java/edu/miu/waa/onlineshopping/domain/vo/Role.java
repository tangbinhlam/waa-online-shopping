package edu.miu.waa.onlineshopping.domain.vo;

public enum  Role {
    ADMIN("ADMIN"),
    SELLER("SELLER"),
    BUYER("BUYER");

    private String role;
    private Role(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return role;
    }

    public static Role of(String role) {
        return Role.valueOf(role);
    }
}
