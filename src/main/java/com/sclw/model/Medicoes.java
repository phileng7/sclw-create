package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Medicoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Double diamDiast;
	private Double diamSist;
	private Double espSepto;
	private Double espParede;
	private Double diamAorta;
	private Double diamAtrio;
	private Double diamVent;
	private Double supCorp;

	@JsonIgnore
	@OneToOne(mappedBy="medicoes")
	private Laudo laudo;

	public Medicoes() {
		super();
	}

	public Medicoes(Integer id, Double diamDiast, Double diamSist, Double espSepto, Double espParede, Double diamAorta,
			Double diamAtrio, Double diamVent, Double supCorp) {
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

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
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
		Medicoes other = (Medicoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
