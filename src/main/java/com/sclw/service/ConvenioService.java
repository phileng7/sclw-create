package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Convenio;
import com.sclw.repository.ConvenioRepository;

@Service
public class ConvenioService {

	@Autowired 
	private ConvenioRepository convenioRepository;
	
	public List<Convenio> findAll() {
		return convenioRepository.findAll();
	}
	
	public Convenio findById(Integer id) {
		Optional<Convenio> obj = convenioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Convenio.class.getName()));
	}
	
	public Convenio insert(Convenio obj) {
		return convenioRepository.save(obj);
	}
	
	public void verifyInsert(Convenio obj) {
		Optional<Convenio> searchObj = convenioRepository.findById(obj.getId());
		if (!searchObj.isPresent())
			convenioRepository.save(obj);
	}
}
