package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Temas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer tipoExameId;
	private Integer auxTipoExameId;

	public Temas() {
		super();
	}

	public Temas(Integer tipoExameId, Integer auxTipoExameId) {
		super();
		this.tipoExameId = tipoExameId;
		this.auxTipoExameId = auxTipoExameId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auxTipoExameId == null) ? 0 : auxTipoExameId.hashCode());
		result = prime * result + ((tipoExameId == null) ? 0 : tipoExameId.hashCode());
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
		Temas other = (Temas) obj;
		if (auxTipoExameId == null) {
			if (other.auxTipoExameId != null)
				return false;
		} else if (!auxTipoExameId.equals(other.auxTipoExameId))
			return false;
		if (tipoExameId == null) {
			if (other.tipoExameId != null)
				return false;
		} else if (!tipoExameId.equals(other.tipoExameId))
			return false;
		return true;
	}
}
