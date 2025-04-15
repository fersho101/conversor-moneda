package com.ferchoo.conversor_moneda.service;

import com.ferchoo.conversor_moneda.exception.ExchangeRateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Cacheable("tasas")
@Service
public class ExchangeRateService {

    @Value("${exchange-rate.api.url}")
    private String apiUrl;

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    private static final List<String> MONEDAS_COMUNES = List.of(
            "USD", "EUR", "MXN", "JPY", "CAD", "GBP", "CNY", "BRL", "COP", "RUB", "AED"
    );

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

    private final String API_URL = "https://v6.exchangerate-api.com/v6";

    public List<String> getMonedasSoportadas() {
        String url = String.format("%s/%s/codes", API_URL, apiKey);

        Map<String, Object> response = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response != null && response.containsKey("supported_codes")) {
            List<List<String>> supportedCodes = (List<List<String>>) response.get("supported_codes");
            return supportedCodes.stream()
                    .map(codePair -> codePair.get(0))
                    .collect(Collectors.toList());
        } else {
            throw new ExchangeRateException("No se pudieron obtener las monedas soportadas.");
        }
    }
}

