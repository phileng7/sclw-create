package com.sclw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sclw.enums.Perfil;

@Entity
@Table(indexes = { 	@Index(name = "idx_usuario",  columnList="usuario", unique = false )} )
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String codPessoa;
	private String nome;
	
	@Column(unique=true)
	private String email;
	
	private String usuario;
		
	@JsonIgnore
	//@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private String dataNascimento;
	private Integer ativado = 1;
	private Float salario;
	
	@OneToMany(mappedBy="pessoa")
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name ="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name ="TELEFONE")
	private Set<String> telefones = new HashSet<>();

	public Pessoa() {
	}

	public Pessoa(Integer id, String codPessoa, String nome, String email, String usuario, String senha, 
			String dataNascimento, Integer ativado, Float salario, Perfil perfil) {
		this.id = id;
		this.codPessoa = codPessoa;
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.ativado = ativado;
		this.salario = salario;
		addPerfil(perfil);
	}
	
	public Pessoa(Pessoa obj) {
		this.id = obj.getId();
		this.codPessoa = obj.getCodPessoa();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.usuario = obj.getUsuario();
		this.senha = obj.getSenha();
		this.dataNascimento = obj.getDataNascimento();
		this.ativado = obj.getAtivado();
		this.salario = obj.getSalario();
		this.telefones.addAll(obj.getTelefones());
		//addPerfis(obj.getPerfis());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(String codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getAtivado() {
		return ativado;
	}

	public void setAtivado(Integer ativado) {
		this.ativado = ativado;
	}		

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getPerfis() {
		//return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		return perfis.stream().map(x -> Perfil.toEnum(x).getDescricao()).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
	public void addPerfis(Set<Integer> perfis) {
		//this.perfis.addAll(perfis.stream().map(x -> x.getCod()).collect(Collectors.toSet()));
		this.perfis=perfis;
	}
	
	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
