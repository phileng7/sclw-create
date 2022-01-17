package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sclw.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {

	List<Procedimento> findAllByTipoExameId(Integer tipo_exame_id);
}
