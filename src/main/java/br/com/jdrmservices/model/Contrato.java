package br.com.jdrmservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.jdrmservices.model.enumeration.TipoMaterial;

@Entity
@Table(name = "contrato")
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Numero é obrigatório")
	private String numero;
	
	@Column(name = "data_inicio")
	@NotNull(message = "A data inicio é obgratória")
	private LocalDate dataInicio = LocalDate.now();

	@Column(name = "data_termino")
	@NotNull(message = "A data término é obgratória")
	private LocalDate dataTermino = LocalDate.now().plusDays(30);

	@Transient
	private String prazo;
	
	@ManyToOne
	@NotNull(message = "A secretaria é obgratória")
	private Secretaria secretaria;
	
	@NotBlank(message = "O objeto do contrato é obrigatório")
	private String objeto;
	
	@ManyToOne
	@NotNull(message = "O fornecedor é obgratório")
	private Fornecedor fornecedor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_material")
	private TipoMaterial tipoMaterial;
	
	@Column(name = "numero_licitacao")
	private String numeroLicitacao;
	
	@Column(name = "valor_contrato")
	private BigDecimal valorContrato;
	
	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
	private List<ItemContrato> itens;
	
	public void adicionarItens(List<ItemContrato> itens) {
		this.itens = itens;
	}

	public boolean isNovo() {
		return codigo == null;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getPrazo() {
		return String.valueOf(ChronoUnit.DAYS.between(this.dataInicio, this.dataTermino));
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public String getNumeroLicitacao() {
		return numeroLicitacao;
	}

	public void setNumeroLicitacao(String numeroLicitacao) {
		this.numeroLicitacao = numeroLicitacao;
	}

	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}
	
	public List<ItemContrato> getItens() {
		return itens;
	}

	public void setItens(List<ItemContrato> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Contrato other = (Contrato) obj;
		if (codigo == null) {
		if (other.codigo != null)
			return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}