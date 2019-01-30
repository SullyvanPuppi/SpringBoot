package com.spuppi.eventosapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.spuppi.eventosapp.model.Convidado;
import com.spuppi.eventosapp.model.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByCpf(String cpf);

}
