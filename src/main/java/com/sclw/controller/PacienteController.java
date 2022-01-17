package com.sclw.controller;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sclw.dto.PacienteDTO;
import com.sclw.exceptions.ConstraintException;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.model.Paciente;
import com.sclw.service.PacienteService;

@Controller
@RequestMapping(value = "/pacientes")
public class PacienteController {

	@Autowired
	PacienteService pacienteService;
	
	@GetMapping
	public ResponseEntity<List<Paciente>> findAll() {
		List<Paciente> list = pacienteService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable Integer id) {
		Paciente obj = pacienteService.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/code/{codP}")
	public ResponseEntity<Paciente> findByCodPaciente(@PathVariable String codP) {
		Paciente obj = pacienteService.findByCodPaciente(codP);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/pagenome")
	public ResponseEntity<Page<Paciente>> findByNamePage(
			@RequestParam(value="nome") String name,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Paciente> lista = pacienteService.findByNamePage(name, page, linesPerPage, orderBy, direction);		
		//Page<PessoaDTO> listDto = lista.map(obj -> new PessoaDTO(obj));
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody Paciente obj) {
		try {
			obj = pacienteService.insert(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível salvar Paciente");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível salvar Paciente");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody Paciente obj) {
		try {
			obj = pacienteService.update(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível atualizar Paciente");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível atualizar Paciente");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/form")
	public ResponseEntity<String> updateForm(@Valid @RequestBody PacienteDTO objForm) {
		Paciente obj = null;
		try {
			obj = pacienteService.updateForm(objForm);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível atualizar Paciente");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível atualizar Paciente");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
