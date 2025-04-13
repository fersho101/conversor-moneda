package com.ferchoo.conversor_moneda.service;

import com.ferchoo.conversor_moneda.model.ConversionRequest;
import com.ferchoo.conversor_moneda.model.ConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversorService {

    @Autowired
    private ExchangeRateService exchangeRateService;

    public ConversionResponse convertir(ConversionRequest request) {
        double tasa = exchangeRateService.getTasaCambio(
                request.getMonedaOrigen(),
                request.getMonedaDestino()
        );

        double resultado = request.getCantidad() * tasa;
        return new ConversionResponse(resultado, "Tasa actual: " + tasa);
    }

}
