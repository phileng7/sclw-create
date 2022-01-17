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
import com.sclw.model.MedicoAssinante;
import com.sclw.repository.EnderecoRepository;
import com.sclw.repository.MedicoAsntRepository;
import com.sclw.repository.PessoaRepository;

@Service
public class MedicoAsntService {

	@Autowired 
	MedicoAsntRepository medicoAsntRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BairroService bairroService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEnc;
	
	//Lista todos
	public List<MedicoAssinante> findAll() {
		return medicoAsntRepository.findAll();
	}
	
	public MedicoAssinante findById(Integer id) {
		Optional<MedicoAssinante> obj = medicoAsntRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + MedicoAssinante.class.getName()));
	}
	
	public MedicoAssinante findByCodMedico(String codMedico) {
		Optional<MedicoAssinante> obj = medicoAsntRepository.findByCodMedico(codMedico);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CodMedicoAnst: " + codMedico + ", Tipo: " + MedicoAssinante.class.getName()));
	}
	
	public MedicoAssinante findByCrm(String crm) {
		Optional<MedicoAssinante> obj = medicoAsntRepository.findByCrm(crm);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CRM: " + crm + ", Tipo: " + MedicoAssinante.class.getName()));
	}
	
	public List<MedicoAssinante> findByName(String nome) {
		return medicoAsntRepository.findAllByNomeStartingWithIgnoreCase(nome);
	}
	
	public Page<MedicoAssinante> findByNamePage(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return medicoAsntRepository.findAllByNomeStartingWithIgnoreCase(nome, pageRequest);
	}
	
	@Transactional
	public MedicoAssinante insert(MedicoAssinante obj) {
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
		obj.addPerfil(Perfil.MEDICOASSINANTE);

		//MedicoAssinante newObj = new MedicoAssinante(obj);
		medicoAsntRepository.save(obj);			
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());	

		return obj;
	}
	
	public MedicoAssinante update(MedicoAssinante obj) {
		Optional<MedicoAssinante> newObj = medicoAsntRepository.findById(obj.getId());
		updateData(newObj.get(), obj);
		medicoAsntRepository.save(newObj.get());
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public void updateData(MedicoAssinante newObj, MedicoAssinante obj) {
		newObj.setAtivado(obj.getAtivado());
		if (obj.getDataNascimento()!=null)
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setEmail(obj.getEmail());
		if (obj.getSalario()!=null)
		newObj.setSalario(obj.getSalario());
		if (obj.getCrm()!=null)
			newObj.setCrm(obj.getCrm());
		newObj.setTelefones(obj.getTelefones());
		
		if (obj.getSenha()!=null ) {
			newObj.setSenha(bCryptEnc.encode(obj.getSenha()));
		}
	}
}
