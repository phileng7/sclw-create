package com.sclw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sclw.enums.EcoValTipos;

@Entity
public class EcoAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer bloco1;
	private Integer bloco2;
	private Integer bloco3;
	private Integer bloco4;
	private Integer bloco5;
	private Integer bloco6;
	private Integer bloco7;
	private Integer bloco8;
	private Integer bloco9;
	private Integer bloco10;
	private Integer bloco11;
	private Integer bloco12;
	private Integer bloco13;
	private Integer bloco14;
	private Integer bloco15;
	@Column(columnDefinition="TEXT")
	private String comentSistVe;
	@Column(columnDefinition="TEXT")
	private String comentMassaVe;
	
	@JsonIgnore
	@OneToOne(mappedBy="medicoes")
	private Laudo ecoavaliacao;
	
	public EcoAvaliacao() {
		super();
	}

	public EcoAvaliacao(Integer id, EcoValTipos bloco1, EcoValTipos bloco2, EcoValTipos bloco3, EcoValTipos bloco4, EcoValTipos bloco5,
			EcoValTipos bloco6, EcoValTipos bloco7, EcoValTipos bloco8, EcoValTipos bloco9, EcoValTipos bloco10, EcoValTipos bloco11,
			EcoValTipos bloco12, EcoValTipos bloco13, EcoValTipos bloco14, EcoValTipos bloco15, String comentSistVe, String comentMassaVe) {
		super();
		this.id = id;
		this.bloco1 = (bloco1==null) ? null : bloco1.getCod();
		this.bloco2 = (bloco2==null) ? null : bloco2.getCod();
		this.bloco3 = (bloco3==null) ? null : bloco3.getCod();
		this.bloco4 = (bloco4==null) ? null : bloco4.getCod();
		this.bloco5 = (bloco5==null) ? null : bloco5.getCod();
		this.bloco6 = (bloco6==null) ? null : bloco6.getCod();
		this.bloco7 = (bloco7==null) ? null : bloco7.getCod();
		this.bloco8 = (bloco8==null) ? null : bloco8.getCod();
		this.bloco9 = (bloco9==null) ? null : bloco9.getCod();
		this.bloco10 = (bloco10==null) ? null : bloco10.getCod();
		this.bloco11 = (bloco11==null) ? null : bloco11.getCod();
		this.bloco12 = (bloco12==null) ? null : bloco12.getCod();
		this.bloco13 = (bloco13==null) ? null : bloco13.getCod();
		this.bloco14 = (bloco14==null) ? null : bloco14.getCod();
		this.bloco15 = (bloco15==null) ? null : bloco15.getCod();
		this.comentSistVe = comentSistVe;
		this.comentMassaVe = comentMassaVe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EcoValTipos getBloco1() {
		return EcoValTipos.toEnum(bloco1);
	}

	public void setBloco1(Integer bloco1) {
		this.bloco1 = bloco1;
	}

	public EcoValTipos getBloco2() {
		return EcoValTipos.toEnum(bloco2);
	}

	public void setBloco2(Integer bloco2) {
		this.bloco2 = bloco2;
	}

	public EcoValTipos getBloco3() {
		return EcoValTipos.toEnum(bloco3);
	}

	public void setBloco3(Integer bloco3) {
		this.bloco3 = bloco3;
	}

	public EcoValTipos getBloco4() {
		return EcoValTipos.toEnum(bloco4);
	}

	public void setBloco4(Integer bloco4) {
		this.bloco4 = bloco4;
	}

	public EcoValTipos getBloco5() {
		return EcoValTipos.toEnum(bloco5);
	}

	public void setBloco5(Integer bloco5) {
		this.bloco5 = bloco5;
	}

	public EcoValTipos getBloco6() {
		return EcoValTipos.toEnum(bloco6);
	}

	public void setBloco6(Integer bloco6) {
		this.bloco6 = bloco6;
	}

	public EcoValTipos getBloco7() {
		return EcoValTipos.toEnum(bloco7);
	}

	public void setBloco7(Integer bloco7) {
		this.bloco7 = bloco7;
	}

	public EcoValTipos getBloco8() {
		return EcoValTipos.toEnum(bloco8);
	}

	public void setBloco8(Integer bloco8) {
		this.bloco8 = bloco8;
	}

	public EcoValTipos getBloco9() {
		return EcoValTipos.toEnum(bloco9);
	}

	public void setBloco9(Integer bloco9) {
		this.bloco9 = bloco9;
	}

	public EcoValTipos getBloco10() {
		return EcoValTipos.toEnum(bloco10);
	}

	public void setBloco10(Integer bloco10) {
		this.bloco10 = bloco10;
	}

	public EcoValTipos getBloco11() {
		return EcoValTipos.toEnum(bloco11);
	}

	public void setBloco11(Integer bloco11) {
		this.bloco11 = bloco11;
	}

	public EcoValTipos getBloco12() {
		return EcoValTipos.toEnum(bloco12);
	}

	public void setBloco12(Integer bloco12) {
		this.bloco12 = bloco12;
	}

	public EcoValTipos getBloco13() {
		return EcoValTipos.toEnum(bloco13);
	}

	public void setBloco13(Integer bloco13) {
		this.bloco13 = bloco13;
	}
	
	public EcoValTipos getBloco14() {
		return EcoValTipos.toEnum(bloco14);
	}

	public void setBloco14(Integer bloco14) {
		this.bloco14 = bloco14;
	}

	public EcoValTipos getBloco15() {
		return EcoValTipos.toEnum(bloco15);
	}

	public void setBloco15(Integer bloco15) {
		this.bloco15 = bloco15;
	}

	public String getComentSistVe() {
		return comentSistVe;
	}

	public void setComentSistVe(String comentSistVe) {
		this.comentSistVe = comentSistVe;
	}

	public String getComentMassaVe() {
		return comentMassaVe;
	}

	public void setComentMassaVe(String comentMassaVe) {
		this.comentMassaVe = comentMassaVe;
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
		EcoAvaliacao other = (EcoAvaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
