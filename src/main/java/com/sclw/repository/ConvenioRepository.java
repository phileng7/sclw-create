package com.sclw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {

}
