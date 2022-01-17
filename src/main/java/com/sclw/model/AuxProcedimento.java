package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AuxProcedimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String mneumonico;
	private String nome;
	private String comMedidas;
	
	@ManyToOne
	@JoinColumn(name="tipo_exame_id")
	private AuxTipoExame tipoExame;
	
	public AuxProcedimento() {
		super();
	}

	public AuxProcedimento(Integer id, String mneumonico, String nome, String comMedidas, AuxTipoExame tipoExame) {
		super();
		this.id = id;
		this.mneumonico = mneumonico;
		this.nome = nome;
		this.comMedidas = comMedidas;
		this.tipoExame = tipoExame;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMneumonico() {
		return mneumonico;
	}

	public void setMneumonico(String mneumonico) {
		this.mneumonico = mneumonico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComMedidas() {
		return comMedidas;
	}

	public void setComMedidas(String comMedidas) {
		this.comMedidas = comMedidas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comMedidas == null) ? 0 : comMedidas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mneumonico == null) ? 0 : mneumonico.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		AuxProcedimento other = (AuxProcedimento) obj;
		if (comMedidas == null) {
			if (other.comMedidas != null)
				return false;
		} else if (!comMedidas.equals(other.comMedidas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mneumonico == null) {
			if (other.mneumonico != null)
				return false;
		} else if (!mneumonico.equals(other.mneumonico))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}	
}
