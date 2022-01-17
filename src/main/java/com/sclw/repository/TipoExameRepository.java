package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sclw.model.TipoExame;

@Repository
public interface TipoExameRepository extends JpaRepository<TipoExame, Integer> {

	List<TipoExame> findAllById(Integer id);
	
	@Query("SELECT max(tpe.id) FROM TipoExame tpe")
	Integer getMaxId();
}
