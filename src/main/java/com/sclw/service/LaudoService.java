package com.sclw.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sclw.dto.FrasesDTO;
import com.sclw.dto.LaudoGruposDTO;
import com.sclw.dto.LaudoUpdateAuxDTO;
import com.sclw.dto.LaudoUpdateConclusaoDTO;
import com.sclw.dto.LaudoUpdateDTO;
import com.sclw.dto.TemasDTO;
import com.sclw.enums.Perfil;
import com.sclw.exceptions.DataInsertionDuplicateException;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Laudo;
import com.sclw.model.LaudoPK;
import com.sclw.repository.LaudoRepository;
import com.sclw.repository.MedicoesRepository;

@Service
public class LaudoService {
	
	private static final Logger LOG = LogManager.getLogger(LaudoService.class);

	@Autowired 
	LaudoRepository laudoRepository;
	
	@Autowired
	MedicoesRepository medicoesRepository;
	
	@Autowired
	ConvenioService convenioService;
	
	@Autowired
	MedicoAsntService medicoAsntService;
	
	@Autowired
	MedicoReqService medicoReqService;
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	TipoExameService tipoExameService;
	
	@Autowired
	ProcedimentoService procedimentoService;
	
	@Autowired
	TemasService temasService;
	
	@Autowired
	FrasesService frasesService;
	
	public List<Laudo> findByCodLaudoMedAsntIdConcluido(String codLaudo, Integer medAsntId, String concluido, String authority) {		
		List<Laudo> list = null;
		if (authority.equals(Perfil.MEDICOASSINANTE.getDescricao()))
			list = laudoRepository.findAllByCodSistemaAndMedicoAssinanteIdAndConcluido(codLaudo, medAsntId,concluido);
		else if (authority.equals(Perfil.ATENDENTE.getDescricao()))
			list = laudoRepository.findAllByCodSistemaAndConcluido(codLaudo, concluido);
		else if (authority.equals(Perfil.GESTOR.getDescricao()))
			list = laudoRepository.findAllByCodSistemaAndConcluido(codLaudo, concluido);
		return list;
	}
	
	public List<Laudo> findByDateMedAsntIdConcluido(String data, Integer medAsntId, String concluido, String authority) {		
		List<Laudo> list = null;
		if (authority.equals(Perfil.MEDICOASSINANTE.getDescricao()))
			list = laudoRepository.findAllByDataAndMedicoAssinanteIdAndConcluido(data, medAsntId,concluido);
		else if (authority.equals(Perfil.GESTOR.getDescricao()))
			list = laudoRepository.findAllByDataAndConcluido(data, concluido);
		else if (authority.equals(Perfil.ATENDENTE.getDescricao()))
			list = laudoRepository.findAllByDataAndConcluido(data, concluido);		
		return list;
	}
	
	public List<Laudo> findByDateRangeMedAsntIdConcluido(String dataStart, String dataEnd, Integer medAsntId, String concluido, String authority) {		
		List<Laudo> list = null;
		if (authority.equals(Perfil.MEDICOASSINANTE.getDescricao()))
			list = laudoRepository.findAllByDataBetweenAndMedicoAssinanteIdAndConcluido(dataStart, dataEnd, medAsntId, concluido);
		else if (authority.equals(Perfil.GESTOR.getDescricao()))
			list = laudoRepository.findAllByDataBetweenAndConcluido(dataStart, dataEnd, concluido);
		else if (authority.equals(Perfil.ATENDENTE.getDescricao()))
			list = laudoRepository.findAllByDataBetweenAndConcluido(dataStart, dataEnd, concluido);		
		return list;
	}	
	
	public Laudo findByIdItem(Integer id, Integer item) {
		Optional<Laudo> obj = laudoRepository.findById(new LaudoPK(id, item));
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + LaudoService.class.getName()));
	}
	
	//INSERT
	public Laudo insert(Laudo obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getId()==null || obj.getId().getId()==null) {
			Laudo maxObj = laudoRepository.findFirstByOrderByIdIdDesc();
			Integer id = maxObj.getId().getId() + 1;
			obj.getId().setId(id);
			obj.getId().setItem(1);
		}
		else if (laudoRepository.existsById(obj.getId()))
			throw new DataInsertionDuplicateException("Não é possível incluir objeto já existente, ID=" + obj.getId());
		
		if (obj.getConvenio()!=null)
			convenioService.verifyInsert(obj.getConvenio());
		
		try {
			medicoAsntService.insert(obj.getMedicoAssinante());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//LOG.error("Laudo insert - Med Asnt nao incluído:" + obj.getMedicoAssinante().getNome());
		}
		
		try {
			if (obj.getMedicoRequisitante().getCodMedico()!=null)
				medicoReqService.insert(obj.getMedicoRequisitante());
			else
				obj.setMedicoRequisitante(null);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//LOG.error("Laudo insert - Med Req nao incluído:" + obj.getMedicoRequisitante().getNome());
		}
		
		try {
			pacienteService.insert(obj.getPaciente());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//LOG.error("Laudo insert - Med Req nao incluído:" + obj.getMedicoRequisitante().getNome());
		}
			
		try {
			tipoExameService.insert(obj.getTipoExame());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//LOG.error("Laudo insert - Med Req nao incluído:" + obj.getMedicoRequisitante().getNome());
		}
		
		try {			
			procedimentoService.insert(obj.getProcedimento());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			//LOG.error("Laudo insert - Med Req nao incluído:" + obj.getMedicoRequisitante().getNome());
		}
				
		return laudoRepository.save(obj);
	}
	
	public Laudo updateMain(LaudoUpdateDTO obj) {
		if (obj==null || obj.getId()==null)
			throw new DataIntegrityException("Update-Objeto de insercao nulo!"); 
		
		LaudoPK ldpk = new LaudoPK(obj.getId(), obj.getItem());
		Optional<Laudo> ld = laudoRepository.findById(ldpk);
		if (ld.get()==null)
			throw new DataIntegrityException("Update not possible because does not exist, ID=" + obj.getId() + ", item=" + obj.getItem());
		if (obj.getAltura()!=null)
			ld.get().setAltura(obj.getAltura());
		if (obj.getIdade()!=null)
			ld.get().setIdade(obj.getIdade());
		if (obj.getPeso()!=null)
			ld.get().setPeso(obj.getPeso());
		if (obj.getMneumonico()!=null)
			ld.get().setMneumonico(obj.getMneumonico());
		ld.get().setNovoLaudo("N"); 	//Not a new laudo anymore
		return laudoRepository.save(ld.get());
	}
	
	public Laudo updateLaudoAux(LaudoUpdateAuxDTO obj) {
		if (obj==null || obj.getId()==null)
			throw new DataIntegrityException("Update-Objeto de insercao nulo!"); 
		
		LaudoPK ldpk = new LaudoPK(obj.getId(), obj.getItem());
		Optional<Laudo> ld = laudoRepository.findById(ldpk);
		if (ld.get()==null)
			throw new DataIntegrityException("Update not possible because does not exist, ID=" + obj.getId() + ", item=" + obj.getItem());
		if (obj.getGrupoTipoExames()!=null)
			ld.get().setGrupoTipoExames(obj.getGrupoTipoExames());
		if (obj.getGrupoTProcedimentos()!=null)
			ld.get().setGrupoTProcedimentos(obj.getGrupoTProcedimentos());
		if (obj.getMneumonico()!=null)
			ld.get().setMneumonico(obj.getMneumonico());
		return laudoRepository.save(ld.get());
	}
	
	public Laudo updateLaudoConclusaoAux(LaudoUpdateConclusaoDTO obj) {
		if (obj==null || obj.getId()==null)
			throw new DataIntegrityException("Update-Objeto de insercao nulo!"); 
		
		LaudoPK ldpk = new LaudoPK(obj.getId(), obj.getItem());
		Optional<Laudo> ld = laudoRepository.findById(ldpk);
		if (ld.get()==null)
			throw new DataIntegrityException("Update not possible because does not exist, ID=" + obj.getId() + ", item=" + obj.getItem());
		if (obj.getConclusao()!=null)
			ld.get().setConclusao(obj.getConclusao());
		return laudoRepository.save(ld.get());
	}
	
	public Laudo update(Laudo obj) {
		if (obj==null || obj.getId()==null)
			throw new DataIntegrityException("Update-Objeto de insercao nulo!"); 
		return laudoRepository.save(obj);
	}
	
	//Delete Medicoes
	@Transactional
	public void deleteMedicoes(Integer id, Integer item) {
		Optional<Laudo> obj = laudoRepository.findById(new LaudoPK(id, item));
		Integer medicoes_id = obj.get().getMedicoes().getId();
		obj.get().setMedicoes(null);
		laudoRepository.save(obj.get());
		medicoesRepository.deleteById(medicoes_id);
		//medicoesService.delete(medicoes_id);
	}
	
	public LaudoGruposDTO searchSaveGrupo(LaudoGruposDTO obj) {
		LaudoGruposDTO objReturn = new LaudoGruposDTO();
		objReturn.setId(obj.getId());
		objReturn.setItem(obj.getItem());
		if (obj==null || obj.getId()==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		Laudo ld = laudoRepository.findAllByIdIdAndIdItem(obj.getId(), obj.getItem());
		if (ld!=null) {
			TemasDTO temasDTO = null;
			FrasesDTO frasesDTO = null;
			if (ld.getGrupoTipoExames()==null) {
				temasDTO = temasService.findByTipoExameId(ld.getTipoExame().getId());
				if (temasDTO!=null) {
					objReturn.setGrupoTipoExames(temasDTO.getAuxTipoExameId());
					ld.setGrupoTipoExames(temasDTO.getAuxTipoExameId());
				}
			}
			if (temasDTO!=null && ld.getGrupoTProcedimentos()==null) {
				frasesDTO = frasesService.findByProcedimentoId(ld.getProcedimento().getId());
				if (frasesDTO!=null) {
					objReturn.setGrupoTProcedimentos(frasesDTO.getAuxProcedimentoId());
					ld.setGrupoTProcedimentos(frasesDTO.getAuxProcedimentoId());
				}
			}
			if (temasDTO!=null || frasesDTO!=null)
				laudoRepository.save(ld);
		}
		return objReturn;
	}
}
