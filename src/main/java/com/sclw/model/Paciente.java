package com.sclw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sclw.enums.Perfil;

@Entity
@JsonTypeName("paciente")
public class Paciente extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	private String codPaciente;
	
	@OneToMany(mappedBy="paciente")
	private List<Laudo> laudos = new ArrayList<>();

	public Paciente() {
	}

	public Paciente(Paciente obj) {
		this.setId(obj.getId());
		this.setCodPessoa(obj.getCodPessoa());
		this.setNome(obj.getNome());
		this.setEmail(obj.getEmail());
		this.setUsuario(obj.getUsuario());
		this.setSenha(obj.getSenha());
		this.setDataNascimento(obj.getDataNascimento());
		this.setAtivado(obj.getAtivado());
		this.setSalario(obj.getSalario());
		this.setTelefones(obj.getTelefones());
		//addPerfis(obj.getPerfis());
	}
	
	public Paciente(Integer id, String codPessoa, String nome, String email, String usuario, String senha, String dataNascimento, 
					Integer ativado, Float salario, Perfil perfil, String codPaciente) {
		super(id, codPessoa, nome, email, usuario, senha, dataNascimento, ativado, salario, perfil);
		this.codPaciente = codPaciente;
	}
	
	public Paciente(Pessoa obj) {
		super(obj);
	}

	public String getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(String codPaciente) {
		this.codPaciente = codPaciente;
	}
}
