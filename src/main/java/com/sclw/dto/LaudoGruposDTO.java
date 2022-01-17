package com.sclw.dto;

import java.io.Serializable;

public class LaudoGruposDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer item;
	
	private Integer grupoTipoExames;
	private Integer grupoTProcedimentos;
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
	public Integer getGrupoTipoExames() {
		return grupoTipoExames;
	}
	public void setGrupoTipoExames(Integer grupoTipoExames) {
		this.grupoTipoExames = grupoTipoExames;
	}
	public Integer getGrupoTProcedimentos() {
		return grupoTProcedimentos;
	}
	public void setGrupoTProcedimentos(Integer grupoTProcedimentos) {
		this.grupoTProcedimentos = grupoTProcedimentos;
	}
}
