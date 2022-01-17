package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Frases;

@Repository
public interface FrasesRepository extends JpaRepository<Frases, Integer> {

	List<Frases> findAll();
	Frases findByProcedimentoId(Integer procedimentoId);
}
