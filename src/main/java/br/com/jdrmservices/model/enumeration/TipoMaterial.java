package br.com.jdrmservices.model.enumeration;

public enum TipoMaterial {
	
	MATERIAIS("Materiais"),
	COMBUSTIVEIS("Combustíveis"),
	SERVICOS("Serviços"),
	SERVICOS_CONTINUOS("Serviços Continuos"),
	OBRAS("Obras"),
	SERVICOS_E_MATERIAIS("Serviços e Materiais"),
	NUNHUM("Nenhum");
	
	private String descricao;
	
	TipoMaterial(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
