package com.sclw.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sclw.model.TipoExame;
import com.sclw.service.TipoExameService;

@Controller
@RequestMapping(value="/tipoexames")
public class TipoExameController {

	@Autowired
	TipoExameService tipoExameService;
	
	@GetMapping
	public ResponseEntity<List<TipoExame>> findAll() {
		List<TipoExame> list = tipoExameService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoExame> findAllById(@PathVariable Integer id) {
		TipoExame obj = tipoExameService.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody TipoExame obj) {
		tipoExameService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody TipoExame obj) {
		tipoExameService.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
