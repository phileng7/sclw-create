package com.sclw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoExame implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String codigo;
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="tipoExame")
	private List<Laudo> laudos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="tipoExame")
	private List<Procedimento> procedimentos = new ArrayList<>();
	
	public TipoExame() {
		super();
	}

	public TipoExame(Integer id, String codigo, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
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
		TipoExame other = (TipoExame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
