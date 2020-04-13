package br.com.jdrmservices.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "compra_direta")
public class CompraDireta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "O número da ordem é obrigatório")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_codigo")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "secretaria_codigo")
	private Secretaria secretaria;
	
	@Column(name = "data_ordem")
	private LocalDate dataOrdem = LocalDate.now();

	@NotBlank(message = "O objeto da compra é obrigatório")
	private String objeto;
	
	@OneToMany(mappedBy = "compraDireta", cascade = CascadeType.ALL)
	private List<ItemCompraDireta> itens;
	
	public void adicionarItens(List<ItemCompraDireta> itens) {
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

	public LocalDate getDataOrdem() {
		return dataOrdem;
	}

	public void setDataOrdem(LocalDate dataOrdem) {
		this.dataOrdem = dataOrdem;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	public List<ItemCompraDireta> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompraDireta> itens) {
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
		CompraDireta other = (CompraDireta) obj;
		if (codigo == null) {
		if (other.codigo != null)
			return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}