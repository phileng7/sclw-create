package com.sclw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(indexes = { 	@Index(name = "idx_cod_sistema",  columnList="codSistema", unique = false )} )
public class Laudo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@JsonIgnore
	@EmbeddedId
	private LaudoPK id = new LaudoPK();
	
	private String uniqueKey;
	
	private String data;
	private String hora;
	private String codSistema;
	private String concluido;
	private Integer idade;
	private Double peso;
	private Double altura;
	private String numGuia;	
	@Column(columnDefinition="TEXT")
	private String conclusao;
	private String dataEntrega;
	private String novoLaudo;
	private String mneumonico;
	
	private Integer grupoTipoExames;
	private Integer grupoTProcedimentos;
	
	@ManyToOne
	@JoinColumn(name="convenio_id")
	private Convenio convenio;
	
	@ManyToOne
	@JoinColumn(name="plano_id")
	private Plano plano;
	
	@ManyToOne
	@JoinColumn(name="medico_assinante_id")
	private MedicoAssinante medicoAssinante;
	
	@ManyToOne
	@JoinColumn(name="medico_requisitante_id")
	private MedicoRequisitante medicoRequisitante;
	
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@OneToMany(mappedBy="id.laudo")
	private List<Foto> fotos = new ArrayList<>();
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="tipo_exame_id")
	private TipoExame tipoExame;
	
	@ManyToOne
	@JoinColumn(name="procedimento_id")
	private Procedimento procedimento;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="medicoes_id")
	private Medicoes medicoes;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="eco_avaliacao_id")
	private EcoAvaliacao ecoavaliacao;

	public Laudo() {
	}

	public Laudo(Integer id, Integer item, String data, String hora, String codSistema, String concluido, Integer idade, Double peso, 
			Double altura, String numGuia, String conclusao, String dataEntrega, String novoLaudo, 
			Convenio convenio, Plano plano, String mneumonico, 
			MedicoAssinante medicoAssinante, MedicoRequisitante medicoRequisitante, Paciente paciente, TipoExame tipoExame, 
			Procedimento procedimento, Medicoes medicoes, EcoAvaliacao ecoavaliacao, String uniqueKey) {
		super();
		this.id.setId(id);
		this.id.setItem(item);
		this.data = data;
		this.hora = hora;
		this.codSistema = codSistema;
		this.concluido = concluido;
		this.idade = idade;
		this.peso = peso;
		this.altura = altura;
		this.numGuia = numGuia;
		this.conclusao = conclusao;
		this.dataEntrega = dataEntrega;
		this.novoLaudo = novoLaudo;
		this.convenio = convenio;
		this.plano = plano;
		this.mneumonico = mneumonico;
		this.medicoAssinante = medicoAssinante;
		this.medicoRequisitante = medicoRequisitante;
		this.paciente = paciente;
		this.tipoExame = tipoExame;
		this.procedimento = procedimento;
		this.medicoes = medicoes;
		this.ecoavaliacao = ecoavaliacao;
		this.uniqueKey = uniqueKey;
	}
	
	public Laudo(Integer id, Integer item, String data, String hora, String codSistema, String concluido, Integer idade, Double peso, 
			Double altura, String numGuia, String conclusao, String dataEntrega, String novoLaudo, 
			Convenio convenio, Plano plano, String mneumonico, 
			MedicoAssinante medicoAssinante, MedicoRequisitante medicoRequisitante, Paciente paciente, TipoExame tipoExame, 
			Procedimento procedimento, Medicoes medicoes, EcoAvaliacao ecoavaliacao, String uniqueKey, 
			Integer grupoTipoExames,Integer grupoTProcedimentos) {
		
		this(id,item,data,hora,codSistema,concluido,idade,peso,altura,numGuia,conclusao,dataEntrega,novoLaudo,convenio,plano,
				mneumonico,medicoAssinante,medicoRequisitante,paciente,tipoExame,procedimento,medicoes,ecoavaliacao,uniqueKey);
		this.grupoTipoExames = grupoTipoExames;
		this.grupoTProcedimentos = grupoTProcedimentos;
	}

	public LaudoPK getId() {
		return id;
	}

	public void setId(LaudoPK id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}	
	
	public String getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(String codSistema) {
		this.codSistema = codSistema;
	}

	public String getConcluido() {
		return concluido;
	}

	public void setConcluido(String concluido) {
		this.concluido = concluido;
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

	public String getNumGuia() {
		return numGuia;
	}

	public void setNumGuia(String numGuia) {
		this.numGuia = numGuia;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getNovoLaudo() {
		return novoLaudo;
	}

	public void setNovoLaudo(String novoLaudo) {
		this.novoLaudo = novoLaudo;
	}
	
	public Convenio getConvenio() {
		return convenio;
	}

	public Plano getPlano() {
		return plano;
	}		

	public MedicoAssinante getMedicoAssinante() {
		return medicoAssinante;
	}
	
	public MedicoRequisitante getMedicoRequisitante() {
		return medicoRequisitante;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public Medicoes getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(Medicoes medicoes) {
		this.medicoes = medicoes;
	}

	public EcoAvaliacao getEcoavaliacao() {
		return ecoavaliacao;
	}

	public Integer getGrupoTipoExames() {
		return grupoTipoExames;
	}

	public void setGrupoTipoExames(Integer grupoTipoExames) {
		this.grupoTipoExames = grupoTipoExames;
	}

	public Integer getGrupoTProcedimentos() {
		return grupoTProcedimentos;
	}

	public void setGrupoTProcedimentos(Integer grupoTProcedimentos) {
		this.grupoTProcedimentos = grupoTProcedimentos;
	}

	public void setMedicoAssinante(MedicoAssinante medicoAssinante) {
		this.medicoAssinante = medicoAssinante;
	}

	public void setMedicoRequisitante(MedicoRequisitante medicoRequisitante) {
		this.medicoRequisitante = medicoRequisitante;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public String getMneumonico() {
		return mneumonico;
	}

	public void setMneumonico(String mneumonico) {
		this.mneumonico = mneumonico;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
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
		Laudo other = (Laudo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
