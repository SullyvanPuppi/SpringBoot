package com.spuppi.apirestfilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spuppi.apirestfilmes.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

}