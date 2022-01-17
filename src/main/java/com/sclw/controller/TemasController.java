package com.sclw.controller;

import java.net.URI;

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

import com.sclw.dto.TemasDTO;
import com.sclw.model.Temas;
import com.sclw.service.TemasService;

@Controller
@RequestMapping(value="/temas")
public class TemasController {

	@Autowired
	TemasService temasService;
	
	@GetMapping("/{tipoExameId}")
	public ResponseEntity<TemasDTO> findByTipoExameId(@PathVariable Integer tipoExameId) {
		TemasDTO list = temasService.findByTipoExameId(tipoExameId);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody Temas obj) {
		Temas retObj = temasService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retObj.getTipoExameId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody Temas obj) {
		temasService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * @PostMapping("/replicate") public ResponseEntity<String>
	 * replicate(@RequestParam Integer sourceTipoExame, @RequestParam Integer
	 * newTipoExame) { temasService.replicate(sourceTipoExame, newTipoExame); return
	 * ResponseEntity.noContent().build(); }
	 */
}
