package com.sclw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sclw.model.Medicoes;

@Repository
public interface MedicoesRepository extends JpaRepository<Medicoes, Integer> {

	List<Medicoes> findAllByLaudoIdIdAndLaudoIdItem(Integer laudo_id, Integer laudo_item);
}
