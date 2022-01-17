package com.sclw.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	MEDICOASSINANTE(2, "ROLE_MEDICOASSINANTE"),
	PACIENTE(3, "ROLE_PACIENTE"),
	MEDICOREQUISITANTE(4, "ROLE_MEDICOREQUISITANTE"),
	ATENDENTE(5, "ROLE_ATENDENTE"),
	GESTOR(6, "ROLE_GESTOR");
	
	private Integer cod;
	private String descricao;
	
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer id) {
		if (id == null) {
			return null;
		}
	
		for (Perfil tc : Perfil.values()) {
			if (id.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
