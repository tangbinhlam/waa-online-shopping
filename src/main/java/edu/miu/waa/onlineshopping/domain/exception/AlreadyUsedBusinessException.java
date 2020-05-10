package edu.miu.waa.onlineshopping.domain.exception;

import lombok.Getter;

@Getter
public class AlreadyUsedBusinessException extends RuntimeException {
    private String id;
    private String message;

    public AlreadyUsedBusinessException(String id, String message) {
        this.id = id;
        this.message = message;
    }
}
