package com.eventoapp.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //uma entidade é uma tabela em um BD, cada instância da entidade corresponde a uma linha dessa tabela.
@Table(name = "Evento")
public class Evento implements Serializable{ //Interface Serialzable

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Gera automaticamente um ID
    private long id;

    private String nome;
    private String data;
    private String local;
    private String horario;
    
}
