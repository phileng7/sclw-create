package com.sclw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sclw.dto.MedicoesDTO;
import com.sclw.model.Laudo;
import com.sclw.model.Medicoes;
import com.sclw.repository.MedicoesRepository;

@Service
public class MedicoesService {

	@Autowired
	MedicoesRepository medicoesRepository;
	
	@Autowired
	LaudoService laudoService;
	
	public List<Medicoes> findAllByLaudoIdAndLaudoItem(Integer laudo_id, Integer laudo_item) {
		List<Medicoes> list = medicoesRepository.findAllByLaudoIdIdAndLaudoIdItem(laudo_id, laudo_item);		
		return list;
	}
	
	public Medicoes insert(MedicoesDTO objDTO) {		
		Medicoes obj = new Medicoes(null, objDTO.getDiamDiast(), objDTO.getDiamSist(), objDTO.getEspSepto(), objDTO.getEspParede(), 
									objDTO.getDiamAorta(), objDTO.getDiamAtrio(), objDTO.getDiamVent(), objDTO.getSupCorp());				
		Laudo laudo = laudoService.findByIdItem(objDTO.getLaudo_id(), objDTO.getLaudo_item());
		obj.setLaudo(laudo);
		medicoesRepository.save(obj);
		laudo.setMedicoes(obj);
		laudoService.update(laudo);
		return obj;
	}
	
	public void update(Medicoes obj) {				
		medicoesRepository.save(obj);
	}
	
	public void delete(Integer id) {				
		medicoesRepository.deleteById(id);
	}
}
