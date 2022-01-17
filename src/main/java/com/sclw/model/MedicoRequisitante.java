package com.sclw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sclw.enums.Perfil;

@Entity
@JsonTypeName("medicoRequisitante")
public class MedicoRequisitante extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	private String codMedico;
	private String crm;
	
	@OneToMany(mappedBy="medicoRequisitante")
	private List<Laudo> laudos = new ArrayList<>();

	public MedicoRequisitante() {
	}

	public MedicoRequisitante(Integer id, String codPessoa, String nome, String email, String usuario, String senha,
			String dataNascimento, Integer ativado, Float salario, Perfil perfil, String crm, String codMedico) {
		super(id, codPessoa, nome, email, usuario, senha, dataNascimento, ativado, salario, perfil);
		this.crm = crm;
		this.codMedico = codMedico;
	}
	
	public MedicoRequisitante(MedicoRequisitante obj) {
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
		this.crm = obj.getCrm();
		//addPerfis(obj.getPerfis());
	}
	
	public MedicoRequisitante(Pessoa obj, String crm) {
		super(obj);
		this.crm = crm;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(String codMedico) {
		this.codMedico = codMedico;
	}
}
