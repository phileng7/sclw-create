package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sclw.enums.TipoResCom;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String logradouro;
	private String complemento;
	private String cep;
	private Integer tipo;
	
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name="bairro_id")
	private Bairro bairro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	public Endereco() {
	}

	public Endereco(Integer id, String logradouro, String complemento,String cep, TipoResCom tipo, Cidade cidade, Bairro bairro, Pessoa pessoa) {
		this.id = id;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.cep = cep;
		this.tipo = (tipo==null) ? null : tipo.getCod();
		this.cidade = cidade;
		this.bairro = bairro;
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public TipoResCom getTipo() {
		return TipoResCom.toEnum(tipo);
	}
/*
	public void setTipo(TipoResCom tipo) {
		this.tipo = tipo.getCod();
	}
*/
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
