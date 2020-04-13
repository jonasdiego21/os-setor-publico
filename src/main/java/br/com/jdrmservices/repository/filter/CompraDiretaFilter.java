package br.com.jdrmservices.repository.filter;

import java.time.LocalDate;

import br.com.jdrmservices.model.Fornecedor;
import br.com.jdrmservices.model.Secretaria;

public class CompraDiretaFilter {

	private LocalDate dataOrdem;
	
	private String numero;
	
	private String objeto;
	
	private Fornecedor fornecedor;
	
	private Secretaria secretaria;

	public LocalDate getDataOrdem() {
		return dataOrdem;
	}

	public void setDataOrdem(LocalDate dataOrdem) {
		this.dataOrdem = dataOrdem;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}
}