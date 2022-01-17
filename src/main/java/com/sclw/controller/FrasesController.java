package com.sclw.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sclw.dto.FrasesDTO;
import com.sclw.model.Frases;
import com.sclw.service.FrasesService;

@Controller
@RequestMapping(value="/frases")
public class FrasesController {

	@Autowired
	FrasesService frasesService;
	
	@GetMapping("/{procedimentoId}")
	public ResponseEntity<FrasesDTO> findByProcedimentoId(@PathVariable Integer procedimentoId) {
		FrasesDTO obj = frasesService.findByProcedimentoId(procedimentoId);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody Frases objDTO) {
		Frases obj = frasesService.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProcedimentoId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@Valid @RequestBody Frases objDTO) {
		frasesService.update(objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@Valid @RequestBody Frases obj) {
		frasesService.delete(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * @PostMapping("/replicate") public ResponseEntity<Void>
	 * replicate(@RequestParam Integer sourceFrasesProc, @RequestParam Integer
	 * destinyFrasesProc) { frasesService.replicate(sourceFrasesProc,
	 * destinyFrasesProc); return ResponseEntity.noContent().build(); }
	 */
}
