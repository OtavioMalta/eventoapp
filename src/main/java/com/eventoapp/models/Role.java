package com.eventoapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Role implements GrantedAuthority{

    @Id
    private String nomeRole;

    // Cria a relação entre Role e Usuário
    @ManyToMany(mappedBy = "roles") // Várias funções para vários usuários
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() { // Método da interface GrantedAuthority
        return this.nomeRole;
    }
    
}
