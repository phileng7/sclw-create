package com.sclw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	public Paciente findByCodPaciente(String codPaciente);
	public Page<Paciente> findAllByNomeStartingWithIgnoreCase(String nome, Pageable pageRequest);
}
