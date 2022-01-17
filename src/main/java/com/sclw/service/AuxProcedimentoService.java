package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.AuxProcedimento;
import com.sclw.repository.AuxProcedimentoRepository;

@Service
public class AuxProcedimentoService {

	@Autowired
	AuxProcedimentoRepository procedimentoRepository;

	public List<AuxProcedimento> findAllByTipoExame(Integer tipo_exame_id) {
		return procedimentoRepository.findAllByTipoExameId(tipo_exame_id);
	}
	
	public AuxProcedimento findById(Integer id) {
		Optional<AuxProcedimento> obj = procedimentoRepository.findById(id);
		if (obj==null || obj.get()==null)
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AuxProcedimento.class.getName());
		return obj.get();
	}
}
