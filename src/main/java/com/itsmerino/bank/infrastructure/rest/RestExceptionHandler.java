package com.itsmerino.bank.infrastructure.rest;

import com.itsmerino.bank.domain.exception.CanNotTransferToSameWalletException;
import com.itsmerino.bank.domain.exception.InsufficientFundsException;
import com.itsmerino.bank.domain.exception.UserNotFoundException;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.infrastructure.rest.dto.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {

    private final ConversionService conversionService;
    private final MessageSource messageSource;

    public RestExceptionHandler(ConversionService conversionService,
                                MessageSource messageSource) {
        this.conversionService = conversionService;
        this.messageSource = messageSource;
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException() {
        String message = getMessage("invalid-argument");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException() {
        String message = getMessage("users.username-already-exists");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException() {
        String message = getMessage("users.not-found");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWalletNotFoundException() {
        String message = getMessage("wallets.not-found");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CanNotTransferToSameWalletException.class)
    public ResponseEntity<ErrorResponse> handleSameWalletException() {
        String message = getMessage("wallets.same-wallet");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException() {
        String message = getMessage("wallets.insufficient-funds");
        ErrorResponse errorResponse = buildErrorResponse(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private String getMessage(String message) {
        return messageSource.getMessage(message, new Object[]{}, Locale.getDefault());
    }

    private ErrorResponse buildErrorResponse(String message) {
        return conversionService.convert(message, ErrorResponse.class);
    }
}
