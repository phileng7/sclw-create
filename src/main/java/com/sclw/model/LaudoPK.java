package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LaudoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer item;
	
	public LaudoPK() {
		super();
	}
	public LaudoPK(Integer id, Integer item) {
		super();
		this.id = id;
		this.item = item;
	}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		LaudoPK other = (LaudoPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
}
