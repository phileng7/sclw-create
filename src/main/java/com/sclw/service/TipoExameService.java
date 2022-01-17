package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.DataIntegrityException;
import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Endereco;
import com.sclw.model.TipoExame;
import com.sclw.repository.TipoExameRepository;

@Service
public class TipoExameService {

	@Autowired
	TipoExameRepository tipoExameRepository;
	
	public List<TipoExame> findAll() {
		return tipoExameRepository.findAll();
	}
	
	public TipoExame findById(Integer id) {
		Optional<TipoExame> obj = tipoExameRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public TipoExame insert(TipoExame obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getId()==null) {
			Integer id = tipoExameRepository.getMaxId() + 1;
			obj.setId(id);
		}
		else if (tipoExameRepository.existsById(obj.getId())) 
			throw new DataIntegrityException("Não é possível incluir tipo exame já existente, ID=" + obj.getId());
		
		tipoExameRepository.save(obj);
		return obj;
	}
	
	public TipoExame update(TipoExame obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getId()==null) {
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		}
		else if (!tipoExameRepository.existsById(obj.getId())) 
			throw new DataIntegrityException("Tipo exame não existe, ID=" + obj.getId());
		
		tipoExameRepository.save(obj);
		return obj;
	}
}
