package com.ferchoo.conversor_moneda.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConversionResponse {
    private double cantidadConvertida;
    private String tasaCambio;

    public ConversionResponse() {
    }

    public ConversionResponse(double cantidadConvertida, String tasaCambio) {
        this.cantidadConvertida = cantidadConvertida;
        this.tasaCambio = tasaCambio;
    }

}

