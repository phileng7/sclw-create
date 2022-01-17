package com.sclw.dto;

import java.io.Serializable;

public class LaudoUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer item;
	private Integer idade;
	private Double peso;
	private Double altura;
	private String mneumonico;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public String getMneumonico() {
		return mneumonico;
	}
	public void setMneumonico(String mneumonico) {
		this.mneumonico = mneumonico;
	}
	
	
}
