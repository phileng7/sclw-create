package com.sclw.dto;

import java.io.Serializable;

public class LaudoUpdateConclusaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer item;
	
	private String conclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}
}
