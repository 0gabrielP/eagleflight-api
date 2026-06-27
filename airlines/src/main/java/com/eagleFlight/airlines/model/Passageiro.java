package com.eagleFlight.airlines.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Aqui são coletados todos os dados de passageiros, bem como a organização das informações no Banco de Dados.

@Entity
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Long idade;
    private String email;
    private String categoriaFidelidade;
    private Integer milhasAcumuladas;

    public Passageiro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoriaFidelidade() {
        return categoriaFidelidade;
    }

    public void setCategoriaFidelidade(String categoriaFidelidade) {
        this.categoriaFidelidade = categoriaFidelidade;
    }

    public Integer getMilhasAcumuladas() {
        return milhasAcumuladas;
    }

    public void setMilhasAcumuladas(Integer milhasAcumuladas) {
        this.milhasAcumuladas = milhasAcumuladas;
    }
}