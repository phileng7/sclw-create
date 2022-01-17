package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Frases implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer procedimentoId;
	private Integer auxProcedimentoId;

	public Frases() {
		super();
	}

	public Frases(Integer procedimentoId, Integer auxProcedimentoId) {
		super();
		this.procedimentoId = procedimentoId;
		this.auxProcedimentoId = auxProcedimentoId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auxProcedimentoId == null) ? 0 : auxProcedimentoId.hashCode());
		result = prime * result + ((procedimentoId == null) ? 0 : procedimentoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frases other = (Frases) obj;
		if (auxProcedimentoId == null) {
			if (other.auxProcedimentoId != null)
				return false;
		} else if (!auxProcedimentoId.equals(other.auxProcedimentoId))
			return false;
		if (procedimentoId == null) {
			if (other.procedimentoId != null)
				return false;
		} else if (!procedimentoId.equals(other.procedimentoId))
			return false;
		return true;
	}
}