package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.infrastructure.rest.dto.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StringToErrorResponseConverterTest {

    private final StringToErrorResponseConverter sut = new StringToErrorResponseConverter();

    @Test
    void itShouldConvertStringToErrorResponse() {
        String message = "error";

        ErrorResponse errorResponse = sut.convert(message);

        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), message);
    }
}