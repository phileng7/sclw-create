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

import com.sclw.dto.PacienteDTO;
import com.sclw.enums.Perfil;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.exceptions.ObjectNotFoundException;
import com.sclw.model.Cidade;
import com.sclw.model.Endereco;
import com.sclw.model.Paciente;
import com.sclw.repository.EnderecoRepository;
import com.sclw.repository.PacienteRepository;
import com.sclw.repository.PessoaRepository;

@Service
public class PacienteService {

	@Autowired 
	PacienteRepository pacienteRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BairroService bairroService;
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEnc;
	
	//Lista todos
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}
	
	public Paciente findById(Integer id) {
		Optional<Paciente> obj = pacienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
	
	public Paciente findByCodPaciente(String codP) {
		Paciente obj = pacienteRepository.findByCodPaciente(codP);
		if (obj==null)
			throw new ObjectNotFoundException("Objeto não encontrado! codPaciente: " + codP + ", Tipo: " + Paciente.class.getName());
		return obj;
		//return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! codPaciente: " + codP + ", Tipo: " + Paciente.class.getName()));
	}
	
	public Page<Paciente> findByNamePage(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return pacienteRepository.findAllByNomeStartingWithIgnoreCase(nome, pageRequest);
	}
	
	@Transactional
	public Paciente insert(Paciente obj) {
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
		obj.addPerfil(Perfil.PACIENTE);
		
		//Paciente newObj = new Paciente(obj);
		pacienteRepository.save(obj);			
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());	

		return obj;
	}
	
	public Paciente update(Paciente obj) {
		Optional<Paciente> newObj = pacienteRepository.findById(obj.getId());
		updateData(newObj.get(), obj);
		pacienteRepository.save(newObj.get());
		
		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}	
	public void updateData(Paciente newObj, Paciente obj) {
		newObj.setAtivado(obj.getAtivado());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setEmail(obj.getEmail());
		newObj.setSalario(obj.getSalario());
		newObj.setTelefones(obj.getTelefones());
	}
	
	public Paciente updateForm(PacienteDTO obj) {
		Optional<Paciente> newObj = pacienteRepository.findById(obj.getId());
		updateDataForm(newObj.get(), obj);
		pacienteRepository.save(newObj.get());
				
		List<Endereco> endList = newObj.get().getEnderecos();
		if (obj.getCidade()!=null) {
			Cidade cidade = cidadeService.findById(obj.getCidade());							
			endList.get(0).setCidade(cidade);						
		}
		if (obj.getLogradouro()!=null) {
			endList.get(0).setLogradouro(obj.getLogradouro());
		}
		if (obj.getComplemento()!=null) {
			if (obj.getBairroNome()==null || obj.getBairroNome()=="") {
				endList.get(0).setBairro(null);
			}
			endList.get(0).setComplemento(obj.getComplemento());
		}
		newObj.get().setEnderecos(endList);
//		obj.getEnderecos().stream().forEach(objB -> bairroService.insert(objB.getBairro()));		 
//		obj.getEnderecos().stream().forEach(objB -> objB.setPessoa(obj));
		
		enderecoRepository.saveAll(endList);
		
		return newObj.get();
	}
	public void updateDataForm(Paciente newObj, PacienteDTO obj) {
		newObj.setAtivado(obj.getAtivado());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setEmail(obj.getEmail());
		newObj.setSalario(obj.getSalario());
//		newObj.setTelefones(obj.getTelefones());
	}
}
