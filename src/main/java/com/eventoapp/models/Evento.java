package com.eventoapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    private String nome;

    @NotEmpty
    private String data;

    @NotEmpty
    private String local;

    @NotEmpty
    private String horario;

    @OneToMany // Um evento para vários convidados
    private List<Convidado> convidados;
    
}
