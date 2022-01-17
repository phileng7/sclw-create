package com.sclw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.MedicoRequisitante;

@Repository
public interface MedicoReqRepository extends JpaRepository<MedicoRequisitante, Integer> {
	
	public Optional<MedicoRequisitante> findByCodMedico(String codMedico);

	public Page<MedicoRequisitante> findAllByNomeStartingWithIgnoreCase(String nome, Pageable pageRequest);
	
	public List<MedicoRequisitante> findAllByNomeContainingIgnoreCase(String name);

	public Optional<MedicoRequisitante> findByCrm(String crm);
}
