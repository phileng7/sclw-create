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
import com.sclw.model.MedicoAssinante;
import com.sclw.service.MedicoAsntService;

@Controller
@RequestMapping(value = "/medasnts")
public class MedicoAsntController {

	@Autowired
	MedicoAsntService medicoAsntService;
	
	@GetMapping
	public ResponseEntity<List<MedicoAssinante>> findAll() {
		List<MedicoAssinante> list = medicoAsntService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoAssinante> findById(@PathVariable Integer id) {
		MedicoAssinante obj = medicoAsntService.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/code/{codAsnt}")
	public ResponseEntity<MedicoAssinante> findById(@PathVariable String codAsnt) {
		MedicoAssinante obj = medicoAsntService.findByCodMedico(codAsnt);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/crm/{crm}")
	public ResponseEntity<MedicoAssinante> findByCrm(@PathVariable String crm) {
		MedicoAssinante obj = medicoAsntService.findByCrm(crm);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<MedicoAssinante>> findByName(
			@RequestParam(value="name") String name) {
		List<MedicoAssinante> lista = medicoAsntService.findByName(name);
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/pagenome")
	public ResponseEntity<Page<MedicoAssinante>> findByNamePage(
			@RequestParam(value="nome") String name,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<MedicoAssinante> lista = medicoAsntService.findByNamePage(name, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody MedicoAssinante obj) {
		try {
			obj = medicoAsntService.insert(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível salvar Medico Assinante");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível salvar Medico Assinante");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody MedicoAssinante obj) {
		try {
			obj = medicoAsntService.update(obj);
		} catch(ConstraintViolationException ex) {
			throw new ConstraintException("Não é possível atualizar Medico Assinante");
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível atualizar Medico Assinante");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
