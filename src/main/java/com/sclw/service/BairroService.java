package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Bairro;
import com.sclw.repository.BairroRepository;

@Service
public class BairroService {
	
	@Autowired
	BairroRepository bairroRepository;

	public List<Bairro> findAll() {
		return bairroRepository.findAll();
	}
	
	public Bairro findById(Integer id) {
		Optional<Bairro> obj = bairroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Bairro.class.getName()));
	}
	
	public void insert(Bairro obj) {
		bairroRepository.save(obj);
	}
}
