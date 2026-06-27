package com.eagleFlight.airlines.service;

import org.springframework.stereotype.Service;

@Service
public class FidelidadeService {

    // Comando para calcular o preço final da passagem com base na categoria
    public double calcularPrecoFinal(double precoOriginal, String categoria) {
        if (categoria == null) {
            return precoOriginal;
        }

        // Padronizando o texto para maiúsculo para evitar erros de digitação (ex: "prata" vs "PRATA")
        String categoriaUpper = categoria.toUpperCase();

        switch (categoriaUpper) {
            case "PRATA":
                return precoOriginal * 0.90; // 10% de desconto (paga 90%)
            case "DIAMANTE":
                return precoOriginal * 0.80; // 20% de desconto (paga 80%)
            case "BRONZE":
            default:
                return precoOriginal; // Sem desconto para Bronze ou desconhecidos
        }
    }

    // 2. Comando para calcular quantas milhas o passageiro vai ganhar com a compra
    public int calcularMilhasGanhas(double precoPago, String categoria) {
        if (categoria == null) {
            return 0;
        }

        String categoriaUpper = categoria.toUpperCase();

        switch (categoriaUpper) {
            case "PRATA":
                return (int) (precoPago * 0.20); // Ganha 20% do valor pago em milhas
            case "DIAMANTE":
                return (int) (precoPago * 0.50); // Ganha 50% do valor pago em milhas
            case "BRONZE":
            default:
                return (int) (precoPago * 0.10); // Ganha 10% do valor pago em milhas
        }
    }
}