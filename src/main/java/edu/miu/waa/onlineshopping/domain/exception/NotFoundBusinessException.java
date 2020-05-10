package edu.miu.waa.onlineshopping.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundBusinessException extends RuntimeException {
    private String id;
    private String message;
}
