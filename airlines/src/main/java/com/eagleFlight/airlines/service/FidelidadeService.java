package com.eagleFlight.airlines.service;

import org.springframework.stereotype.Service;

@Service
public class FidelidadeService {

    public double calcularPrecoFinal(double precoOriginal, String categoria) {
        if (categoria == null) {
            return precoOriginal;
        }

        String categoriaUpper = categoria.toUpperCase();

        switch (categoriaUpper) {
            case "PRATA":
                return precoOriginal * 0.90;
            case "DIAMANTE":
                return precoOriginal * 0.80;
            case "BRONZE":
            default:
                return precoOriginal;
        }
    }

    public int calcularMilhasGanhas(double precoPago, String categoria) {
        if (categoria == null) {
            return 0;
        }

        String categoriaUpper = categoria.toUpperCase();

        switch (categoriaUpper) {
            case "PRATA":
                return (int) (precoPago * 0.20);
            case "DIAMANTE":
                return (int) (precoPago * 0.50);
            case "BRONZE":
            default:
                return (int) (precoPago * 0.10);
        }
    }
}