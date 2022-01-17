package com.sclw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.MedicoAssinante;

@Repository
public interface MedicoAsntRepository extends JpaRepository<MedicoAssinante, Integer> {
	
	public Optional<MedicoAssinante> findByCodMedico(String codMedico);
	
	public List<MedicoAssinante> findAllByNomeStartingWithIgnoreCase(String nome);
	
	public Page<MedicoAssinante> findAllByNomeStartingWithIgnoreCase(String nome, Pageable pageRequest);

	public Optional<MedicoAssinante> findByCrm(String crm);
}
