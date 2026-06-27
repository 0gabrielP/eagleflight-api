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
    private passageiroRepository passageiroRepo;

    @Autowired
    private FidelidadeService fidelidadeService;

    @GetMapping
    public List<Viagem> listarViagens() {
        return viagemRepo.findAll();
    }

    @PostMapping
    public Viagem cadastrarViagem(@RequestBody Viagem viagem) {
        // 1. Verificar se a viagem possui um passageiro vinculado
        if (viagem.getPassageiro() != null && viagem.getPassageiro().getId() != null) {

            // 2. Buscar o passageiro completo no banco de dados
            Optional<Passageiro> passageiroOpt = passageiroRepo.findById(viagem.getPassageiro().getId());

            if (passageiroOpt.isPresent()) {
                Passageiro passageiro = passageiroOpt.get();

                // 3. Calcular preço final com desconto baseado na categoria
                double precoFinal = fidelidadeService.calcularPrecoFinal(viagem.getPrecoOriginal(), passageiro.getCategoriaFidelidade());
                viagem.setPrecoFinal(precoFinal);

                // 4. Calcular milhas ganhas nesta viagem
                int milhasGanhas = fidelidadeService.calcularMilhasGanhas(precoFinal, passageiro.getCategoriaFidelidade());

                // 5. Atualizar o saldo de milhas do passageiro e salvar
                int saldoAtualizado = (passageiro.getMilhasAcumuladas() != null ? passageiro.getMilhasAcumuladas() : 0) + milhasGanhas;
                passageiro.setMilhasAcumuladas(saldoAtualizado);
                passageiroRepo.save(passageiro);
            }
        }

        // Se por acaso a lógica não setou o preço final, ele assume o preço original
        if (viagem.getPrecoFinal() == 0) {
            viagem.setPrecoFinal(viagem.getPrecoOriginal());
        }

        return viagemRepo.save(viagem);
    }
}