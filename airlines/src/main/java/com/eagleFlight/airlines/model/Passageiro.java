package com.eagleFlight.airlines.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String nome;
    private Long idade;
    private String email;

    public Passageiro() {
    }
        public Long getId() {return Id;}
        public void setId (Long Id) {this.Id = Id;}

        public String getnome() {return nome;}
        public void setnome(String nome) {this.nome = nome;}

        public Long idade() {return idade;}
        public void setidade (Long idade) {this.idade = idade;}

        public String email() {return email;}
        public void setemail(String email) {this.email = email;}


}
