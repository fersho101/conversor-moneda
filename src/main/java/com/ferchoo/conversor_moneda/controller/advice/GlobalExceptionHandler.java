package com.ferchoo.conversor_moneda.controller.advice;

import com.ferchoo.conversor_moneda.exception.ExchangeRateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExchangeRateException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, String> handlerExchangeRateException(ExchangeRateException ex) {
        return Map.of(
                "error", "Error al obtener monedas",
                "message", ex.getMessage()
        );
    }
}
