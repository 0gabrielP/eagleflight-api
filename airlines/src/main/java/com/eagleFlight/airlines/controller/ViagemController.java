package com.eagleFlight.airlines.controller;

import com.eagleFlight.airlines.model.Viagem;
import com.eagleFlight.airlines.model.Passageiro;
import com.eagleFlight.airlines.repository.viagemRepository;
import com.eagleFlight.airlines.repository.passageiroRepository;
import com.eagleFlight.airlines.service.FidelidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private viagemRepository viagemRepo;

    @Autowired
    private passageiroRepository passageiroRepo; // Injetando o robô dos passageiros

    @Autowired
    private FidelidadeService fidelidadeService; // Injetando o nosso cérebro de regras de negócio

    @GetMapping
    public List<Viagem> listarViagens() {
        return viagemRepo.findAll();
    }

    @PostMapping
    public Viagem cadastrarViagem(@RequestBody Viagem viagem) {

        if (viagem.getPassageiro() != null && viagem.getPassageiro().getId() != null) {

            Optional<Passageiro> passageiroOpt = passageiroRepo.findById(viagem.getPassageiro().getId());

            if (passageiroOpt.isPresent()) {
                Passageiro passageiro = passageiroOpt.get();

                double precoFinal = fidelidadeService.calcularPrecoFinal(viagem.getPrecoOriginal(), passageiro.getCategoriaFidelidade());
                viagem.setPrecoFinal(precoFinal);

                int milhasGanhas = fidelidadeService.calcularMilhasGanhas(precoFinal, passageiro.getCategoriaFidelidade());

                int saldoAtualizado = (passageiro.getMilhasAcumuladas() != null ? passageiro.getMilhasAcumuladas() : 0) + milhasGanhas;
                passageiro.setMilhasAcumuladas(saldoAtualizado);
                passageiroRepo.save(passageiro);
            }
        }

        if (viagem.getPrecoFinal() == 0) {
            viagem.setPrecoFinal(viagem.getPrecoOriginal());
        }

        return viagemRepo.save(viagem);
    }
}