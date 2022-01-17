package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	//@Transactional(readOnly=true)
	//@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	//public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
	
	public List<Cidade> findAllByEstadoId(Integer estado_id);
}
