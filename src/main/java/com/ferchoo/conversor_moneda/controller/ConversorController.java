package com.ferchoo.conversor_moneda.controller;

import com.ferchoo.conversor_moneda.model.ConversionRequest;
import com.ferchoo.conversor_moneda.model.ConversionResponse;
import com.ferchoo.conversor_moneda.service.ConversorService;
import com.ferchoo.conversor_moneda.service.ExchangeRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    @Autowired
    private ConversorService conversorService;
    private final ExchangeRateService exchangeRateService;

    public ConversorController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping("/convertir")
    public ConversionResponse convertirMoneda(@Valid @RequestBody ConversionRequest request) {
        return conversorService.convertir(request);
    }

    @Cacheable("monedas")
    @GetMapping("/monedas-soportadas")
    public ResponseEntity<List<String>> getMonedasSoportadas() {
        List<String> monedas = exchangeRateService.getMonedasSoportadas();
        return ResponseEntity.ok(monedas);
    }
}
