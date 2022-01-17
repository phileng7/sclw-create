package com.sclw.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sclw.enums.Perfil;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.MedicoRequisitante;
import com.sclw.repository.EnderecoRepository;
import com.sclw.repository.MedicoReqRepository;
import com.sclw.repository.PessoaRepository;

@Service
public class MedicoReqService {

	@Autowired 
	MedicoReqRepository medicoReqRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BairroService bairroService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEnc;
	
	//Lista todos
	public List<MedicoRequisitante> findAll() {
		return medicoReqRepository.findAll();
	}
	
	public MedicoRequisitante findById(Integer id) {
		Optional<MedicoRequisitante> obj = medicoReqRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + MedicoRequisitante.class.getName()));
	}
	
	public MedicoRequisitante findByCodMedico(String codMedico) {
		Optional<MedicoRequisitante> obj = medicoReqRepository.findByCodMedico(codMedico);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CodMedicoReq: " + codMedico + ", Tipo: " + MedicoRequisitante.class.getName()));
	}
	
	public MedicoRequisitante findByCrm(String crm) {
		Optional<MedicoRequisitante> obj = medicoReqRepository.findByCrm(crm);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CRM: " + crm + ", Tipo: " + MedicoRequisitante.class.getName()));
	}
	
	public List<MedicoRequisitante> findByName(String name) {
		return medicoReqRepository.findAllByNomeContainingIgnoreCase(name);
	}
	
	public Page<MedicoRequisitante> findByNamePage(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return medicoReqRepository.findAllByNomeStartingWithIgnoreCase(nome, pageRequest);
	}
	
	@Transactional
	public MedicoRequisitante insert(MedicoRequisitante obj) {
		if (obj==null)
			throw new DataIntegrityException("Objeto de insercao nulo!"); 
		else if (obj.getId()==null) {
			Integer id = pessoaRepository.getMaxId() + 1;
			obj.setId(id);
		}
		else if (pessoaRepository.existsById(obj.getId())) 
			throw new DataIntegrityException("Não é possível incluir pessoa já existente, ID=" + obj.getId());
		
		if (obj.getUsuario()!=null ) {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByteUser = decoder.decode(obj.getUsuario());
			String decodedStringUser = new String(decodedByteUser);
			obj.setUsuario(decodedStringUser);
		}
		if (obj.getSenha()!=null ) {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByteSenha = decoder.decode(obj.getSenha());
			String decodedStringSenha = new String(decodedByteSenha);
			obj.setSenha(bCryptEnc.encode(decodedStringSenha));
		}
		obj.addPerfil(Perfil.MEDICOREQUISITANTE);
		
		//MedicoRequisitante newObj = new MedicoRequisitante(obj);
		medicoReqRepository.save(obj);			
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());	

		return obj;
	}
	
	public MedicoRequisitante update(MedicoRequisitante obj) {
		Optional<MedicoRequisitante> newObj = medicoReqRepository.findById(obj.getId());
		updateData(newObj.get(), obj);
		medicoReqRepository.save(newObj.get());
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public void updateData(MedicoRequisitante newObj, MedicoRequisitante obj) {
		newObj.setAtivado(obj.getAtivado());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setEmail(obj.getEmail());
		newObj.setSalario(obj.getSalario());
		newObj.setCrm(obj.getCrm());
	}
}
