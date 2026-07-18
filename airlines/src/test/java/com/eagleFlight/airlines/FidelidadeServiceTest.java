package com.eagleFlight.airlines;

import com.eagleFlight.airlines.model.Passageiro;
import com.eagleFlight.airlines.repository.passageiroRepository;
import com.eagleFlight.airlines.service.FidelidadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Diz ao JUnit para usar as ferramentas de teste do Mockito
@ExtendWith(MockitoExtension.class)
public class FidelidadeServiceTest {

    // 1. O Mockito cria um 'dublê' do repositório de passageiros
    @Mock
    private passageiroRepository passageiroRepository;

    // 2. O Mockito cria o FidelidadeService real e coloca o 'dublê' lá dentro
    @InjectMocks
    private FidelidadeService fidelidadeService;

    // --- TESTE COM SIMULAÇÃO DE BANCO (MOCKITO) ---

    @Test
    public void deveCalcularDescontoUsandoPassageiroDoBancoSimulado() {
        // ARRANGE (Preparação)
        // Criamos o passageiro fictício com categoria "PRATA" (10% de desconto)
        Passageiro passageiroFicticio = new Passageiro();
        passageiroFicticio.setId(1L);
        passageiroFicticio.setNome("Gabriel");
        passageiroFicticio.setCategoriaFidelidade("PRATA");

        // Configuramos o 'Dublê': "Quando buscarem o ID 1, retorne o passageiroFicticio"
        Mockito.when(passageiroRepository.findById(1L)).thenReturn(Optional.of(passageiroFicticio));

        // ACT (Ação)
        // Chamamos o novo método passando o ID e o valor original (R$ 1000.0)
        double precoFinalCalculado = fidelidadeService.calcularPrecoFinal(1L, 1000.0);

        // ASSERT (Verificação)
        // Esperamos R$ 900.0 (10% de desconto sobre R$ 1000.0)
        double precoEsperado = 900.0;
        assertEquals(precoEsperado, precoFinalCalculado, 0.0001);
    }

    // --- TESTES DE PREÇO FINAL DIRETO (SEM BANCO) ---

    @Test
    public void deveCalcularDescontoDeVintePorCentoParaCategoriaDiamante() {
        // Arrange
        double precoOriginal = 1000.0;
        String categoria = "DIAMANTE";

        // Act
        double precoFinalCalculado = fidelidadeService.calcularPrecoFinal(precoOriginal, categoria);

        // Assert (R$ 1000.0 com 20% de desconto vira R$ 800.0)
        double precoEsperado = 800.0;
        assertEquals(precoEsperado, precoFinalCalculado, 0.0001);
    }

    @Test
    public void deveCalcularDescontoDeDezPorCentoParaCategoriaPrata() {
        // Arrange
        double precoOriginal = 1000.0;
        String categoria = "PRATA";

        // Act
        double precoFinalCalculado = fidelidadeService.calcularPrecoFinal(precoOriginal, categoria);

        // Assert (R$ 1000.0 com 10% de desconto vira R$ 900.0)
        double precoEsperado = 900.0;
        assertEquals(precoEsperado, precoFinalCalculado, 0.0001);
    }

    // --- TESTES DE ACÚMULO DE MILHAS ---

    @Test
    public void deveCalcularCinquentaPorCentoDeMilhasParaCategoriaDiamante() {
        // Arrange
        double precoPago = 1000.0;
        String categoria = "DIAMANTE";

        // Act
        int milhasCalculadas = fidelidadeService.calcularMilhasGanhas(precoPago, categoria);

        // Assert (O Diamante ganha 50% de R$ 1000 em milhas, ou seja, 500 milhas!)
        int milhasEsperadas = 500;
        assertEquals(milhasEsperadas, milhasCalculadas);
    }

    // --- TESTES DO CAMINHO TRISTE (VALIDAÇÕES) ---

    @Test
    public void deveLancarExcecaoQuandoPrecoForNegativo() {
        // Arrange
        double precoInvalido = -100.0;
        String categoria = "DIAMANTE";

        // Act & Assert
        IllegalArgumentException excecao = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    fidelidadeService.calcularPrecoFinal(precoInvalido, categoria);
                }
        );

        assertEquals("O valor não pode ser negativo!", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoCategoriaForNula() {
        // Arrange
        double precoOriginal = 1000.0;
        String categoriaNula = null;

        // Act & Assert
        IllegalArgumentException excecao = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    fidelidadeService.calcularPrecoFinal(precoOriginal, categoriaNula);
                }
        );

        assertEquals("A categoria de fidelidade não pode estar vazia!", excecao.getMessage());
    }
}