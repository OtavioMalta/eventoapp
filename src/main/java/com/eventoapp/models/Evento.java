package com.eventoapp.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    public long id(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
