package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sclw.dto.CidadeNewDTO;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Cidade;
import com.sclw.model.Estado;
import com.sclw.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository repository;
	
	public List<Cidade> findAllByEstadoId(Integer estado_id) {
		return repository.findAllByEstadoId(estado_id);
	}
	
	public Cidade insert(Cidade obj) {		
		return repository.save(obj);
	}
	
	public Cidade fromDTO(CidadeNewDTO obj) {
		return new Cidade(obj.getId(),obj.getNome(),new Estado(obj.getEstado_id(),null,null));
	}
	
	public Cidade findById(Integer id) {		
		Optional<Cidade> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	//Update
	private void updateData(Cidade upObj, Cidade obj) {
		upObj.setNome(obj.getNome());
	}
	
	public Cidade update(Cidade obj) {
		Cidade upObj = findById(obj.getId());
		updateData(upObj, obj);
		return repository.save(upObj);
	}
	
	//Delete
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cidade pois possui associacao");
		}
	}
}
