package com.eagleFlight.airlines.controller;

import com.eagleFlight.airlines.model.Passageiro;
import com.eagleFlight.airlines.repository.passageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/passageiros")
public class PassageiroController {

    @Autowired
    private passageiroRepository passageiroRepo;

    @GetMapping
    public List<Passageiro> listarPassageiros() {
        return passageiroRepo.findAll();
    }

    @PostMapping
    public Passageiro cadastrarPassageiro(@Valid @RequestBody Passageiro passageiro) {
        // Validações básicas padrão para o cadastro inicial
        if (passageiro.getCategoriaFidelidade() == null || passageiro.getCategoriaFidelidade().isEmpty()) {
            passageiro.setCategoriaFidelidade("BRONZE");
        }

        if (passageiro.getMilhasAcumuladas() == null) {
            passageiro.setMilhasAcumuladas(0);
        }

        return passageiroRepo.save(passageiro);
    }
}