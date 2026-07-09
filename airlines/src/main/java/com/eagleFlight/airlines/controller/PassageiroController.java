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
    @DeleteMapping("/{id}")
    public void deletarPassageiro(@PathVariable Long id) {
        passageiroRepo.deleteById(id);
    }
    @PutMapping("/{id}")
    public Passageiro atualizarPassageiro(@PathVariable Long id, @RequestBody Passageiro dadosAtualizados) {
        // 1. Busca o passageiro existente pelo ID
        Passageiro passageiroExistente = passageiroRepo.findById(id)
                .orElseThrow(); // se não achar, joga erro

        // 2. Atualiza apenas os campos que fazem sentido mudar
        passageiroExistente.setNome(dadosAtualizados.getNome());
        passageiroExistente.setEmail(dadosAtualizados.getEmail());
        passageiroExistente.setCpf(dadosAtualizados.getCpf());
        passageiroExistente.setIdade(dadosAtualizados.getIdade());

        // 3. Salva de volta no banco (o .save faz update se o ID já existir)
        return passageiroRepo.save(passageiroExistente);
    }
}