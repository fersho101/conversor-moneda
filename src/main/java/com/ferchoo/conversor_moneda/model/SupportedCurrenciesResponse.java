package com.ferchoo.conversor_moneda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedCurrenciesResponse {
    private List<List<String>> supported_codes;
}
