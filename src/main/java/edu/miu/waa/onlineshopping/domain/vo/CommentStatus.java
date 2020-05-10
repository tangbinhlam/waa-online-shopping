package edu.miu.waa.onlineshopping.domain.vo;

public enum CommentStatus {
    ADDED("ADDED"),
    APPROVED("APPROVED"),
    REJECT("REJECT");

    private String status;
    private CommentStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

    public static CommentStatus of(String role) {
        return CommentStatus.valueOf(role);
    }
}
