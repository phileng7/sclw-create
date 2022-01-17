package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.MedicoRequisitante;
import com.sclw.model.Procedimento;
import com.sclw.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	
	@Autowired
	ProcedimentoRepository procedimentoRepository;

	public List<Procedimento> findAllByTipoExame(Integer tipo_exame_id) {
		return procedimentoRepository.findAllByTipoExameId(tipo_exame_id);
	}
	
	public Procedimento findById(Integer id) {
		Optional<Procedimento> obj = procedimentoRepository.findById(id);
		if (obj==null || obj.get()==null)
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Procedimento.class.getName());
		return obj.get();
	}
	
	public Procedimento insert(Procedimento obj) {
		return procedimentoRepository.save(obj);
	}
	
	public Procedimento update(Procedimento obj) {
		Optional<Procedimento> newObj = procedimentoRepository.findById(obj.getId());
		if (newObj==null)
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + obj.getId() + ", Tipo: " + MedicoRequisitante.class.getName());
		newObj.get().setMneumonico(obj.getMneumonico());
		newObj.get().setNome(obj.getNome());
		newObj.get().setComMedidas(obj.getComMedidas());
		return procedimentoRepository.save(newObj.get());
	}
}
