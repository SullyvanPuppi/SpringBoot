package com.spuppi.apirestfilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spuppi.apirestfilmes.model.Filme;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 15 de fev de 2019
 *
 */
public interface FilmeRepository extends JpaRepository<Filme, Long>{

}