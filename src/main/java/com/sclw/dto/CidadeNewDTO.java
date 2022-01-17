package com.sclw.dto;

import java.io.Serializable;

import com.sclw.model.Cidade;

public class CidadeNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	private Integer estado_id;
	
	public CidadeNewDTO() {
	}

	public CidadeNewDTO(Cidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.estado_id = obj.getEstado().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Integer estado_id) {
		this.estado_id = estado_id;
	}		
}
