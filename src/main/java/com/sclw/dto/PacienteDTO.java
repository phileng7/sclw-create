package com.sclw.dto;

import java.io.Serializable;

public class PacienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String email;
	private String usuario;
	private String cpf;
	private String rg;
	private String sexo;
	private String dataNascimento;
	private Integer ativado = 1;
	private Float salario;
	private Integer cidade;
	private String logradouro;
	private String complemento;
	private String bairroNome;
	
	public PacienteDTO() {
	}

	public PacienteDTO(Integer id, String nome, String email, String usuario, String cpf, String rg, String sexo,
			String dataNascimento, Integer ativado, Float salario, Integer cidadeId, String logradouro, String complemento,
			String bairroNome) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.cpf = cpf;
		this.rg = rg;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.ativado = ativado;
		this.salario = salario;		
		this.cidade = cidadeId;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairroNome = bairroNome;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public Integer getCidade() {
		return cidade;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidade = cidadeId;
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

	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}

	public String getBairroNome() {
		return bairroNome;
	}

	public void setBairroNome(String bairroNome) {
		this.bairroNome = bairroNome;
	}
}
