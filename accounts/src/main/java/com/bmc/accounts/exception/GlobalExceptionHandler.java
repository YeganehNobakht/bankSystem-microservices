package com.bmc.accounts.exception;

import com.bmc.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
            CustomerAlreadyException e, WebRequest webRequest){
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .errorMessage(e.getMessage())
                        .errorTime(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST
        );

    }
}
