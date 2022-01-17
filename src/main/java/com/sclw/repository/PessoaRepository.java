package com.sclw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sclw.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	public Page<Pessoa> findAllByNomeStartingWithIgnoreCase(String nome, Pageable pageRequest);

	public Pessoa findByUsuario(String usuario);
	
	@Query("SELECT max(pes.id) FROM Pessoa pes")
	Integer getMaxId();
}
