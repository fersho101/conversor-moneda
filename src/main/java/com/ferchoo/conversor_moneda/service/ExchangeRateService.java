package com.ferchoo.conversor_moneda.service;

import com.ferchoo.conversor_moneda.exception.ExchangeRateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Cacheable("tasas")
@Service
public class ExchangeRateService {

    @Value("${exchange-rate.api.url}")
    private String apiUrl;

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    public Double getTasaCambio(String monedaOrigen, String monedaDestino) {
        String url = String.format("%s/%s/pair/%s/%s", apiUrl, apiKey, monedaOrigen, monedaDestino);

        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> {
                    return response.bodyToMono(String.class).flatMap(
                            errorBody -> Mono.error(new ExchangeRateException("Moneda no soportada: " + errorBody)));
                })
                .bodyToMono(Map.class)
                .map(body -> {
                    if (body.containsKey("conversion_rate")) {
                        return (double) body.get("conversion_rate");
                    } else {
                        throw new ExchangeRateException("Tasa de cambio no disponible para las monedas especificadas.");
                    }
                })
                .block();

    }
}

