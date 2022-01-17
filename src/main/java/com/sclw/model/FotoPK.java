package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class FotoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumns({	@JoinColumn(name="laudo_id", referencedColumnName = "id"),
					@JoinColumn(name = "laudo_item", referencedColumnName = "item") })
	private Laudo laudo;
	
	private Integer id;

	public FotoPK() {
		super();
	}

	public FotoPK(Laudo laudo, Integer id) {
		super();
		this.laudo = laudo;
		this.id = id;
	}

	@JsonIgnore
	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((laudo.getId().getId() == null) ? 0 : laudo.getId().getId().hashCode());
		result = prime * result + ((laudo.getId().getItem() == null) ? 0 : laudo.getId().getItem().hashCode());
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
		FotoPK other = (FotoPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (laudo == null) {
			if (other.laudo != null)
				return false;
		} else if (!laudo.getId().getId().equals(other.laudo.getId().getId()))
			return false;
		else if (!laudo.getId().getItem().equals(other.laudo.getId().getItem()))
			return false;
		return true;
	}

	 
}
