package com.sclw.dto;

import java.io.Serializable;

import javax.persistence.Id;

public class FrasesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer procedimentoId;
	private Integer auxProcedimentoId;
	private String nameAuxProcedimento;
	
	public FrasesDTO(Integer procedimentoId, Integer auxProcedimentoId, String nameAuxProcedimento) {
		super();
		this.procedimentoId = procedimentoId;
		this.auxProcedimentoId = auxProcedimentoId;
		this.nameAuxProcedimento = nameAuxProcedimento;
	}
	public Integer getProcedimentoId() {
		return procedimentoId;
	}
	public void setProcedimentoId(Integer procedimentoId) {
		this.procedimentoId = procedimentoId;
	}
	public Integer getAuxProcedimentoId() {
		return auxProcedimentoId;
	}
	public void setAuxProcedimentoId(Integer auxProcedimentoId) {
		this.auxProcedimentoId = auxProcedimentoId;
	}
	public String getNameAuxProcedimento() {
		return nameAuxProcedimento;
	}
	public void setNameAuxProcedimento(String nameAuxProcedimento) {
		this.nameAuxProcedimento = nameAuxProcedimento;
	}
}
