package com.ferchoo.conversor_moneda.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConversionRequest {
    @NotBlank
    private String monedaOrigen;
    @NotBlank
    private String monedaDestino;
    @Positive
    private double cantidad;

}
