package com.eagleFlight.airlines.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

//Aqui são coletados todos os dados de passageiros, bem como a organização das informações no Banco de Dados

@Entity
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O nome do passageiro é obrigatório!")
    private String nome;

    @Min(value = 0, message = "A idade não pode ser negativa!")
    private Long idade;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @Email(message = "O e-mail informado precisa ser valido!")
    @NotBlank(message = "O e-mail é obrigatório!")
    private String email;

    private String categoriaFidelidade;

    private Integer milhasAcumuladas;

    public Passageiro() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Long getIdade() {return idade;}
    public void setIdade(Long idade) {this.idade = idade;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCategoriaFidelidade() {return categoriaFidelidade;}
    public void setCategoriaFidelidade(String categoriaFidelidade) {this.categoriaFidelidade = categoriaFidelidade;}

    public Integer getMilhasAcumuladas() {return milhasAcumuladas;}
    public void setMilhasAcumuladas(Integer milhasAcumuladas) {this.milhasAcumuladas = milhasAcumuladas;}

}