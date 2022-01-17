package com.sclw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Convenio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "CONVENIO_PLANO",
		joinColumns = @JoinColumn(name = "convenio_id"),
		inverseJoinColumns = @JoinColumn(name = "plano_id") 
	)
	private List<Plano> planos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="convenio")
	private List<Laudo> laudos;
	
	public Convenio() {
	}

	public Convenio(Integer id, String nome) {
		this.id = id;
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

	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
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
		Convenio other = (Convenio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
