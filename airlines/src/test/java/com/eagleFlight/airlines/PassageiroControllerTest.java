package com.eagleFlight.airlines;

import com.eagleFlight.airlines.controller.PassageiroController;
import com.eagleFlight.airlines.model.Passageiro;
import com.eagleFlight.airlines.repository.passageiroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(PassageiroController.class)
public class PassageiroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private passageiroRepository passageiroRepository;

    @Test
    public void deveRetornarStatus200EDadosDoPassageiroAoBuscarPorIdExistente() throws Exception {
        // ARRANGE
        Passageiro passageiroFicticio = new Passageiro();
        passageiroFicticio.setId(1L);
        passageiroFicticio.setNome("Gabriel");
        passageiroFicticio.setEmail("gabriel@email.com");
        passageiroFicticio.setCategoriaFidelidade("DIAMANTE");

        Mockito.when(passageiroRepository.findById(1L)).thenReturn(Optional.of(passageiroFicticio));

        // ACT & ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/passageiros/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Gabriel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoriaFidelidade").value("DIAMANTE"));
    }
}