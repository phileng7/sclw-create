package com.sclw.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sclw.dto.LaudoGruposDTO;
import com.sclw.dto.LaudoUpdateAuxDTO;
import com.sclw.dto.LaudoUpdateConclusaoDTO;
import com.sclw.dto.LaudoUpdateDTO;
import com.sclw.model.Laudo;
import com.sclw.service.LaudoService;

@Controller
@RequestMapping(value = "/laudos")
public class LaudoController {
	
	@Autowired
	LaudoService laudoService;

	/*
	//Gestor
	@GetMapping
	public ResponseEntity<List<Laudo>> findAll() {
		List<Laudo> list = laudoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Laudo>> findAllByIdId(@PathVariable Integer id) {
		List<Laudo> list = laudoService.findAllByIdId(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Laudo> findByIdIdAndItem(@RequestParam (value="id") Integer id, @RequestParam (value="item") Integer item) {
		Laudo obj = laudoService.findByIdItem(id,item);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/medasnt")
	public ResponseEntity<List<Laudo>> findByCodLaudoMedAsntId(
			@RequestParam(value="codLaudo") String codLaudo,
			@RequestParam(value="medAsntId") Integer medAsntId) {
		List<Laudo> list = laudoService.findByCodLaudoMedAsntId(codLaudo,medAsntId);
		return ResponseEntity.ok(list);
	}
	
	/*
	@GetMapping("/data/{data}")
	public ResponseEntity<List<Laudo>> findByDate(@PathVariable String data) {
		List<Laudo> list = laudoService.findByDate(data);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/datapage")
	public ResponseEntity<Page<Laudo>> findByDatePage(
			@RequestParam(value="data") String data,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Laudo> list = laudoService.findByDatePage(data, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/datarange")
	public ResponseEntity<List<Laudo>> findByDateRange(
			@RequestParam (value="begin") String begin,
			@RequestParam (value="end") String end) {
		List<Laudo> list = laudoService.findByDateRange(begin,end);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/datarangepage")
	public ResponseEntity<Page<Laudo>> findByDateRangePage(
			@RequestParam (value="begin") String begin,
			@RequestParam (value="end") String end,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Laudo> list = laudoService.findByDateRangePage(begin, end, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	//Data atual range 1 semana antes
	@GetMapping("/datasemanapage")
	public ResponseEntity<Page<Laudo>> findByDateSemanaPage(
			@RequestParam(value="data") String data,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Laudo> list = laudoService.findByDateSemanaPage(data, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	*/
	
	@GetMapping("/medasntconc")
	public ResponseEntity<List<Laudo>> findByCodLaudoMedAsntIdConcluido(
			@RequestParam(value="codLaudo") String codLaudo,
			@RequestParam(value="medAsntId") Integer medAsntId,
			@RequestParam(value="concluido") String concluido,
			@RequestParam(value="authority") String authority) {
		List<Laudo> list = laudoService.findByCodLaudoMedAsntIdConcluido(codLaudo,medAsntId,concluido,authority);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/medasntdtconc")
	public ResponseEntity<List<Laudo>> findByDateMedAsntIdConcluido(
			@RequestParam(value="data") String data,
			@RequestParam(value="medAsntId") Integer medAsntId,
			@RequestParam(value="concluido") String concluido,
			@RequestParam(value="authority") String authority) {
		List<Laudo> list = laudoService.findByDateMedAsntIdConcluido(data,medAsntId,concluido,authority);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/medasntdtrconc")
	public ResponseEntity<List<Laudo>> findByDateRangeMedAsntIdConcluido(
			@RequestParam(value="dataStart") String dataStart,
			@RequestParam(value="dataEnd") String dataEnd,
			@RequestParam(value="medAsntId") Integer medAsntId,
			@RequestParam(value="concluido") String concluido,
			@RequestParam(value="authority") String authority) {
		List<Laudo> list = laudoService.findByDateRangeMedAsntIdConcluido(dataStart,dataEnd,medAsntId,concluido,authority);
		return ResponseEntity.ok(list);
	}	
	
	//INSERT
	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody Laudo obj) {		
		obj = laudoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{item}").buildAndExpand(obj.getId().getId(),obj.getId().getItem()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/main")
	public ResponseEntity<String> updateMain(@Valid @RequestBody LaudoUpdateDTO obj) {
		Laudo ld = laudoService.updateMain(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{item}").buildAndExpand(ld.getId().getId(),ld.getId().getItem()).toUri();
		return ResponseEntity.created(uri).build();
	}
		
	@PutMapping("/aux")
	public ResponseEntity<String> updateMain(@Valid @RequestBody LaudoUpdateAuxDTO obj) {
		Laudo ld = laudoService.updateLaudoAux(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{item}").buildAndExpand(ld.getId().getId(),ld.getId().getItem()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/conc")
	public ResponseEntity<String> updateConclusao(@Valid @RequestBody LaudoUpdateConclusaoDTO obj) {
		Laudo ld = laudoService.updateLaudoConclusaoAux(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{item}").buildAndExpand(ld.getId().getId(),ld.getId().getItem()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/grupos")
	public ResponseEntity<LaudoGruposDTO> searchSaveGrupo(@Valid @RequestBody LaudoGruposDTO obj) {
		LaudoGruposDTO ldDTO = laudoService.searchSaveGrupo(obj);
		return ResponseEntity.ok(ldDTO);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody Laudo obj) {
		obj = laudoService.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{item}").buildAndExpand(obj.getId().getId(),obj.getId().getItem()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Delete Medicoes
	@DeleteMapping("/medicoes")
	public ResponseEntity<Void> deleteMedicoes(@RequestParam (value="id") Integer id, @RequestParam (value="item") Integer item) {		
		laudoService.deleteMedicoes(id,item);
		return ResponseEntity.noContent().build();
	}
}
