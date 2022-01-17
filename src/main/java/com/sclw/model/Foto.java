package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Foto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private FotoPK id;
	
	private String path;
	
	private Short pagina1;
	private Short grande;
	
	@Column(columnDefinition="TEXT")
	private String tema;
	
	@Column(columnDefinition="TEXT")
	private String frase;
	
	public Foto() {
		super();
	}
	
	public Foto(FotoPK id, String path, Short pagina1, Short grande, String tema, String frase) {
		super();
		this.id = id;
		this.path = path;
		this.pagina1 = pagina1;
		this.grande = grande;
		this.tema = tema;
		this.frase = frase;
	}
	
	public Foto(Laudo laudo, Integer id, String path, Short pagina1, Short grande, String tema, String frase) {
		super();
		this.id = new FotoPK(laudo, id);
		this.path = path;
		this.pagina1 = pagina1;
		this.grande = grande;
		this.tema = tema;
		this.frase = frase;
	}

	public FotoPK getId() {
		return id;
	}

	public void setId(FotoPK id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Short getPagina1() {
		return pagina1;
	}

	public void setPagina1(Short pagina1) {
		this.pagina1 = pagina1;
	}

	public Short getGrande() {
		return grande;
	}

	public void setGrande(Short grande) {
		this.grande = grande;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Foto other = (Foto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
