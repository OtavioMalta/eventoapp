package com.eventoapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Convidado {
    
    @Id
    private String rg;

    private String nomeConvidado;

    @ManyToOne // Vários convidados para um evento
    private Evento evento;
}
