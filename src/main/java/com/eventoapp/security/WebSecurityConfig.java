package com.eventoapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/cadastrarEvento").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cadastrarEvento").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/deletarEvento").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/deletarConvidado").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cadastrarConvidado").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/deletarEvento").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/deletarConvidado").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/cadastrarConvidado").hasRole("ADMIN")

		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	/* AUTENTIFICAÇÃO EM MEMÓRIA
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
        .withUser("otavio").password("{noop}123").roles("ADMIN");
    }*/
            
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

    @Override
	public void configure(WebSecurity web) throws Exception{ // Desabilita o bloqueio do spring security em pastas estáticas
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}
}
