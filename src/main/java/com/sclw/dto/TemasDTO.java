package com.sclw.dto;

import java.io.Serializable;

public class TemasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tipoExameId;
	private Integer auxTipoExameId;
	private String nameAuxTipoExame;
	
	public TemasDTO() {
		super();
	}

	public TemasDTO(Integer tipoExameId, Integer auxTipoExameId, String nameAuxTipoExame) {
		super();
		this.tipoExameId = tipoExameId;
		this.auxTipoExameId = auxTipoExameId;
		this.nameAuxTipoExame=nameAuxTipoExame;
	}

	public Integer getTipoExameId() {
		return tipoExameId;
	}

	public void setTipoExameId(Integer tipoExameId) {
		this.tipoExameId = tipoExameId;
	}

	public Integer getAuxTipoExameId() {
		return auxTipoExameId;
	}

	public void setAuxTipoExameId(Integer auxTipoExameId) {
		this.auxTipoExameId = auxTipoExameId;
	}

	public String getNameAuxTipoExame() {
		return nameAuxTipoExame;
	}

	public void setNameAuxTipoExame(String nameAuxTipoExame) {
		this.nameAuxTipoExame = nameAuxTipoExame;
	}
}
