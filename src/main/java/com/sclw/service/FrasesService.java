package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sclw.dto.FrasesDTO;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.model.AuxProcedimento;
import com.sclw.model.Frases;
import com.sclw.repository.FrasesRepository;

@Service
public class FrasesService {

	@Autowired
	FrasesRepository frasesRepository;
	
	@Autowired
	AuxProcedimentoService auxProcedimentoService;
	
	public List<Frases> findAll() {
		return frasesRepository.findAll();
	}
	
	public FrasesDTO findByProcedimentoId(Integer procedimentoId) {
		Frases obj = frasesRepository.findByProcedimentoId(procedimentoId);
		if (obj==null) return null;
		AuxProcedimento auxProced = auxProcedimentoService.findById(obj.getAuxProcedimentoId());
		FrasesDTO objDTO = new FrasesDTO(procedimentoId, obj.getAuxProcedimentoId(), auxProced.getNome());
		return objDTO;
	}
	
	public Frases insert(Frases obj) {
		Frases objFr;
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getProcedimentoId()!=null) {
			if (frasesRepository.existsById(obj.getProcedimentoId())) {
				objFr = update(obj);
				//throw new DataIntegrityException("Não é possível incluir frase já existente, ID=" + obj.getProcedimentoId());
			} else {
				objFr = frasesRepository.save(obj);
			}
		} else {
			throw new DataIntegrityException("Insercao: Procedimento com valor nulo!"); 
		}			
		return objFr;
	}
	
	public Frases update(Frases obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getProcedimentoId()==null) {
			throw new DataIntegrityException("Objeto de insercao sem o ID!"); 
		}
		else if (!frasesRepository.existsById(obj.getProcedimentoId())) 
			throw new DataIntegrityException("Tema não existe, ID=" + obj.getProcedimentoId());
		Optional<Frases> newObj = frasesRepository.findById(obj.getProcedimentoId());
		if (!newObj.isPresent())
			throw new DataIntegrityException("Atualizacao: Procedimento nao encontrado!"); 
		newObj.get().setAuxProcedimentoId(obj.getAuxProcedimentoId());
		frasesRepository.save(newObj.get());
		return newObj.get();
	}
	
	public void delete(Frases obj) {
		try {
			frasesRepository.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma frase que possui entidades relacionadas");
		}
	}
	
	/*
	 * public void replicate(Integer sourceFrasesProc, Integer destinyFrasesProc) {
	 * List<Frases> frases =
	 * frasesRepository.findAllByProcedimentoId(sourceFrasesProc); if (frases==null)
	 * throw new DataIntegrityException("Procedimento não existe para replicar!");
	 * 
	 * Procedimento proc = procedimentoService.findById(destinyFrasesProc); if
	 * (proc==null) throw new DataIntegrityException("Procedimento não existe!");
	 * 
	 * for (Frases frase : frases) { Frases newObj = new Frases(null,
	 * frase.getNome(), proc); frasesRepository.save(newObj); } }
	 */
}
