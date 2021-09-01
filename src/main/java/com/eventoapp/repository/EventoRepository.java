package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{  //Essa interface passa métodos já prontos como salvar, deletar, update.. no BD
	public Evento findById(long id);
}
