package com.spuppi.eventosapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.spuppi.eventosapp.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
	
	Evento findByCodigo(long codigo);

}