package com.sclw.enums;

public enum EcoValTipos {

	NORMAL(1, "Normal"),
	HIPOCINETICO(2, "Hipocinético"),
	ACINETICO(3, "Acinético"),
	DISCINETICO(4, "Discinético"),
	NAOAVAL(5,"Não Avaliado");
	
	private Integer cod;
	private String descricao;
	
	private EcoValTipos(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EcoValTipos toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EcoValTipos tc : EcoValTipos.values()) {
			if (id.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Cod inválido " + id);
	}
}
