package com.sclw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Temas;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Integer> {

	Temas findByTipoExameId(Integer id);
}
