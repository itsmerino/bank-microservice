package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.infrastructure.rest.dto.ErrorResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToErrorResponseConverter implements Converter<String, ErrorResponse> {

    @Override
    public ErrorResponse convert(String message) {
        return ErrorResponse.builder()
                .message(message)
                .build();
    }
}
