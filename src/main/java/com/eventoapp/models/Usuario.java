package com.eventoapp.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;

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

    private String senha;

    @ManyToMany
    @JoinTable( name = "usuarios_roles", joinColumns = @JoinColumn(
	        name = "usuario_id", referencedColumnName = "login"), 
	        inverseJoinColumns = @JoinColumn(
	        name = "role_id", referencedColumnName = "nomeRole")) 
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
