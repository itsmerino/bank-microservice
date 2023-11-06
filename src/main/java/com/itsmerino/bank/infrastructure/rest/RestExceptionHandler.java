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
        return buildBadRequestResponse("invalid-argument");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException() {
        return buildBadRequestResponse("users.username-already-exists");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException() {
        return buildBadRequestResponse("users.not-found");
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWalletNotFoundException() {
        return buildBadRequestResponse("wallets.not-found");
    }

    @ExceptionHandler(CanNotTransferToSameWalletException.class)
    public ResponseEntity<ErrorResponse> handleSameWalletException() {
        return buildBadRequestResponse("wallets.same-wallet");
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException() {
        return buildBadRequestResponse("wallets.insufficient-funds");
    }

    private ResponseEntity<ErrorResponse> buildBadRequestResponse(String i18n) {
        String message = messageSource.getMessage(i18n, new Object[]{}, Locale.getDefault());
        ErrorResponse errorResponse = conversionService.convert(message, ErrorResponse.class);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
