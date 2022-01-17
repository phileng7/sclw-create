package com.sclw.dto;

import java.io.Serializable;

public class MedicoesDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private Double diamDiast;
	private Double diamSist;
	private Double espSepto;
	private Double espParede;
	private Double diamAorta;
	private Double diamAtrio;
	private Double diamVent;
	private Double supCorp;
	
	private Integer laudo_id;
	private Integer laudo_item;
	
	public MedicoesDTO() {
		super();
	}

	public MedicoesDTO(Integer id, Double diamDiast, Double diamSist, Double espSepto, Double espParede,
			Double diamAorta, Double diamAtrio, Double diamVent, Double supCorp, Integer laudo_id, Integer laudo_item) {
		super();
		this.id = id;
		this.diamDiast = diamDiast;
		this.diamSist = diamSist;
		this.espSepto = espSepto;
		this.espParede = espParede;
		this.diamAorta = diamAorta;
		this.diamAtrio = diamAtrio;
		this.diamVent = diamVent;
		this.supCorp = supCorp;
		this.laudo_id = laudo_id;
		this.laudo_item = laudo_item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDiamDiast() {
		return diamDiast;
	}

	public void setDiamDiast(Double diamDiast) {
		this.diamDiast = diamDiast;
	}

	public Double getDiamSist() {
		return diamSist;
	}

	public void setDiamSist(Double diamSist) {
		this.diamSist = diamSist;
	}

	public Double getEspSepto() {
		return espSepto;
	}

	public void setEspSepto(Double espSepto) {
		this.espSepto = espSepto;
	}

	public Double getEspParede() {
		return espParede;
	}

	public void setEspParede(Double espParede) {
		this.espParede = espParede;
	}

	public Double getDiamAorta() {
		return diamAorta;
	}

	public void setDiamAorta(Double diamAorta) {
		this.diamAorta = diamAorta;
	}

	public Double getDiamAtrio() {
		return diamAtrio;
	}

	public void setDiamAtrio(Double diamAtrio) {
		this.diamAtrio = diamAtrio;
	}

	public Double getDiamVent() {
		return diamVent;
	}

	public void setDiamVent(Double diamVent) {
		this.diamVent = diamVent;
	}

	public Double getSupCorp() {
		return supCorp;
	}

	public void setSupCorp(Double supCorp) {
		this.supCorp = supCorp;
	}

	public Integer getLaudo_id() {
		return laudo_id;
	}

	public void setLaudo_id(Integer laudo_id) {
		this.laudo_id = laudo_id;
	}

	public Integer getLaudo_item() {
		return laudo_item;
	}

	public void setLaudo_item(Integer laudo_item) {
		this.laudo_item = laudo_item;
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
		MedicoesDTO other = (MedicoesDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
