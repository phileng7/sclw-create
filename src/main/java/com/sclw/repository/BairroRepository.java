package com.sclw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {

}
