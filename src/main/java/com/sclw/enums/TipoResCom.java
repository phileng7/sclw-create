package com.sclw.enums;

public enum TipoResCom {

	RESIDENCIAL(1, "Residencial"),
	COMERCIAL(2, "Comercial");
	
	private Integer cod;
	private String descricao;

	private TipoResCom(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoResCom toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (TipoResCom tc : TipoResCom.values()) {
			if (id.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Cod inválido " + id);
	}
}
