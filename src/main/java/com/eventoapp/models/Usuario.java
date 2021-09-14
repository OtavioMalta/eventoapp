package com.eventoapp.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario implements UserDetails{

    @Id
    private String login;

    private String nomeCompleto;

    @NotEmpty
    private String senha;

    // Conexão entre Usuário e Role
    @ManyToMany // Vários usuários para várias funções
    @JoinTable( name = "usuarios_roles", joinColumns = @JoinColumn( // Nome da tabela intermediária entre role e usuário
	        name = "usuario_id", referencedColumnName = "login"), // Cria e nomeia as tabelas
	        inverseJoinColumns = @JoinColumn(
	        name = "role_id", referencedColumnName = "nomeRole")) // Cria e nomeia as tabelas
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
