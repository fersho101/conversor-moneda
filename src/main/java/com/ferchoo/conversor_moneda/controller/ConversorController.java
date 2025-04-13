package com.ferchoo.conversor_moneda.controller;

import com.ferchoo.conversor_moneda.model.ConversionRequest;
import com.ferchoo.conversor_moneda.model.ConversionResponse;
import com.ferchoo.conversor_moneda.service.ConversorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    @Autowired
    private ConversorService conversorService;

    @PostMapping("/convertir")
    public ConversionResponse convertirMoneda(@Valid @RequestBody ConversionRequest request) {
        return conversorService.convertir(request);
    }
}
