package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.AuxTipoExame;
import com.sclw.repository.AuxTipoExameRepository;

@Service
public class AuxTipoExameService {

	@Autowired
	AuxTipoExameRepository auxTipoExameRepository;

	public List<AuxTipoExame> findAll() {
		return auxTipoExameRepository.findAll();
	}
	
	public AuxTipoExame findById(Integer id) {
		Optional<AuxTipoExame> obj = auxTipoExameRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AuxTipoExame.class.getName()));
	}
}
