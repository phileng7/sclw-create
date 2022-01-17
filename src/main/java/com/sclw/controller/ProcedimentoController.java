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

import com.sclw.model.Procedimento;
import com.sclw.service.ProcedimentoService;

@Controller
@RequestMapping(value="/procedimentos")
public class ProcedimentoController {

	@Autowired
	ProcedimentoService procedimentoService;
	
	@GetMapping("/{tipoExameId}")
	public ResponseEntity<List<Procedimento>> findAllByTipoExame(@PathVariable Integer tipoExameId) {
		List<Procedimento> lista = procedimentoService.findAllByTipoExame(tipoExameId);
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody Procedimento obj) {
		procedimentoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody Procedimento obj) {
		procedimentoService.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
