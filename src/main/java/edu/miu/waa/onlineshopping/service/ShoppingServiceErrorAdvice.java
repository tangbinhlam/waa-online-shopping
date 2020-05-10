package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.exception.AlreadyUsedBusinessException;
import edu.miu.waa.onlineshopping.domain.exception.NotFoundBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShoppingServiceErrorAdvice {

    @ExceptionHandler({NotFoundBusinessException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundBusinessException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler({AlreadyUsedBusinessException.class})
    public ResponseEntity<String> handleNotFoundException(AlreadyUsedBusinessException e) {
        return error(HttpStatus.IM_USED, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
