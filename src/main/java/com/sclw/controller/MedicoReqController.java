package com.sclw.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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

import com.sclw.exceptions.ConstraintException;
import com.sclw.exceptions.DataIntegrityException;
import com.sclw.model.MedicoRequisitante;
import com.sclw.service.MedicoReqService;

@Controller
@RequestMapping(value = "/medreqs")
public class MedicoReqController {

	@Autowired
	MedicoReqService medicoReqService;
	
	@GetMapping
	public ResponseEntity<List<MedicoRequisitante>> findAll() {
		List<MedicoRequisitante> list = medicoReqService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoRequisitante> findById(@PathVariable Integer id) {
		MedicoRequisitante obj = medicoReqService.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/code/{codReq}")
	public ResponseEntity<MedicoRequisitante> findById(@PathVariable String codReq) {
		MedicoRequisitante obj = medicoReqService.findByCodMedico(codReq);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/crm/{crm}")
	public ResponseEntity<MedicoRequisitante> findByCrm(@PathVariable String crm) {
		MedicoRequisitante obj = medicoReqService.findByCrm(crm);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<MedicoRequisitante>> findByName(@RequestParam(value="name") String name) {
		List<MedicoRequisitante> lista = medicoReqService.findByName(name);
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/pagenome")
	public ResponseEntity<Page<MedicoRequisitante>> findByNamePage(
			@RequestParam(value="nome") String name,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<MedicoRequisitante> lista = medicoReqService.findByNamePage(name, page, linesPerPage, orderBy, direction);		
		//Page<PessoaDTO> listDto = lista.map(obj -> new PessoaDTO(obj));
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody MedicoRequisitante obj) {
		try {
			obj = medicoReqService.insert(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível salvar Medico Requisitante");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível salvar Medico Requisitante");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody MedicoRequisitante obj) {
		try {
			obj = medicoReqService.update(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível atualizar Medico Requisitante");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível atualizar Medico Requisitante");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
