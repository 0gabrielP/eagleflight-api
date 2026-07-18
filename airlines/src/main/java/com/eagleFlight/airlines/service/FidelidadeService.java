package com.eagleFlight.airlines.service;

import com.eagleFlight.airlines.model.Passageiro;
import com.eagleFlight.airlines.repository.passageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FidelidadeService {

    @Autowired
    private passageiroRepository passageiroRepo;

    public double calcularPrecoFinal (Long passageiroId, double precoOriginal) {
        Passageiro passageiro = passageiroRepo.findById(passageiroId)
        .orElseThrow(() -> new IllegalArgumentException("Passageiro não encontrado!"));
        return calcularPrecoFinal(precoOriginal, passageiro.getCategoriaFidelidade());
    }
    // Comando para calcular o preço da passagem com base na categoria e validações para tratar dados incorretos
    public double calcularPrecoFinal(double precoOriginal, String categoria) {
        if (precoOriginal <0) {
            throw new IllegalArgumentException("O valor não pode ser negativo!");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria de fidelidade não pode estar vazia!");
        }

        // Padronizando o texto para evitar erros de digitação (ex: "prata" vs "PRATA")
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

    // 2. Comando para calcular ganho de milhas do passageiro e validação para tratar dados incorretos
    public int calcularMilhasGanhas(double precoPago, String categoria) {
        if (precoPago <0) {
            throw new IllegalArgumentException("O valor não pode ser negativo!");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria de fidelidade não pode estar vazia!");
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