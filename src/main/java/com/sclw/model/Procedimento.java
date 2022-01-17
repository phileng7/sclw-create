package com.sclw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String codProcedimento;
	private String mneumonico;
	private String nome;
	private String comMedidas;
	
	@ManyToOne
	@JoinColumn(name="tipo_exame_id")
	private TipoExame tipoExame;
	
	@OneToMany(mappedBy="procedimento")
	private List<Laudo> laudos = new ArrayList<>();

	public Procedimento() {
		super();
	}

	public Procedimento(Integer id, String codProcedimento, String mneumonico, String nome, String comMedidas, TipoExame tipoExame) {
		super();
		this.id = id;
		this.codProcedimento = codProcedimento;
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

	public String getCodProcedimento() {
		return codProcedimento;
	}

	public void setCodProcedimento(String codProcedimento) {
		this.codProcedimento = codProcedimento;
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

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
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
		Procedimento other = (Procedimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
