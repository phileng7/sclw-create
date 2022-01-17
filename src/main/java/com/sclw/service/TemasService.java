package com.sclw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.dto.TemasDTO;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.model.AuxTipoExame;
import com.sclw.model.Temas;
import com.sclw.repository.TemasRepository;

@Service
public class TemasService {

	@Autowired
	TemasRepository temasRepository;
	
	@Autowired
	AuxTipoExameService auxTipoExameService;
	
	public TemasDTO findByTipoExameId(Integer tipoExameId) {
		Temas obj = temasRepository.findByTipoExameId(tipoExameId);
		if (obj==null) return null;
		AuxTipoExame auxTpEx = auxTipoExameService.findById(obj.getAuxTipoExameId());
		TemasDTO objDTO = new TemasDTO(tipoExameId, obj.getAuxTipoExameId(), auxTpEx.getNome());
		return objDTO;
	}
	
	public Temas insert(Temas obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getTipoExameId()==null) 
			throw new DataIntegrityException("Objeto de insercao sem o Tipo Exame ID!"); 
		else if (obj.getAuxTipoExameId()==null) 
			throw new DataIntegrityException("Objeto de insercao sem o Tipo Exame ID Auxiliar!"); 		

		Temas objT = new Temas(obj.getTipoExameId(), obj.getAuxTipoExameId()); 
		temasRepository.save(objT);
		return(obj);
	}
	
	public Temas update(Temas obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getTipoExameId()==null) {
			throw new DataIntegrityException("Objeto de insercao sem o ID!"); 
		}
		else if (!temasRepository.existsById(obj.getTipoExameId())) {
			//throw new DataIntegrityException("Tema não existe, ID=" + objDTO.getTipoExameId());
			Temas newObj = insert(obj);
			return newObj;
		} else {
			Optional<Temas> newObj = temasRepository.findById(obj.getTipoExameId());
			newObj.get().setAuxTipoExameId(obj.getAuxTipoExameId());
			temasRepository.save(newObj.get());
			return newObj.get();
		}		
	}
	
	/*
	 * public void replicate(Integer sourceTipoExame, Integer newTipoExame) {
	 * List<Temas> temas =
	 * temasRepository.findAllByTipoExameTemasId(sourceTipoExame); if (temas==null)
	 * throw new DataIntegrityException("Tema não existe para replicar!");
	 * 
	 * TipoExame tpe = tipoExameService.findById(newTipoExame); if (tpe==null) throw
	 * new DataIntegrityException("Tipo de exame não existe!");
	 * 
	 * for (Temas tema : temas) { Temas newObj = new Temas(null, tema.getNome(),
	 * tpe); temasRepository.save(newObj); } }
	 */
}
