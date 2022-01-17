package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sclw.model.AuxProcedimento;

public interface AuxProcedimentoRepository extends JpaRepository<AuxProcedimento, Integer> {

	List<AuxProcedimento> findAllByTipoExameId(Integer tipo_exame_id);
}
