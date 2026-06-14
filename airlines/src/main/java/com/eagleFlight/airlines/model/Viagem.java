package com.eagleFlight.airlines.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private Long NumeroVoo;
    private String origem;
    private String destino;
    private Long precoPassagem;
    private Long assentosDisponveis;

    public Viagem() {
    }
    public Long getId() {return Id;}
    public void setId(Long Id) {this.Id = Id;}

    public Long setNumeroVoo() {return NumeroVoo;}
    public void setNumerovoo(Long NumeroVoo) {this.NumeroVoo = NumeroVoo;}

    public String getorigem() {return origem;}
    public void setorigem(String origem) {this.origem = origem;}

    public String getdestino() {return destino;}
    public void setdestino(String destino) {this.destino = destino;}

    public Long getprecoPassagem() {return precoPassagem;}
    public void setprecoPassagem(Long precoPassagem) {this.precoPassagem = precoPassagem;}

    public Long assentosDisponveis() {return assentosDisponveis;}
    public void setassentosDisponveis(Long assentosDisponveis) {this.assentosDisponveis = assentosDisponveis;}
}
