package br.com.jdrmservices.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.jdrmservices.model.Fornecedor;
import br.com.jdrmservices.model.Secretaria;
import br.com.jdrmservices.model.enumeration.TipoMaterial;

public class ContratoFilter {

	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	private String numero;
	
	private String numeroLicitacao;
	
	private String objeto;
	
	private TipoMaterial tipoMaterial;
	
	private BigDecimal valorContrato;
	
	private Fornecedor fornecedor;
	
	private Secretaria secretaria;

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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroLicitacao() {
		return numeroLicitacao;
	}

	public void setNumeroLicitacao(String numeroLicitacao) {
		this.numeroLicitacao = numeroLicitacao;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}
}