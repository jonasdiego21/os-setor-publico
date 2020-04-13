package br.com.jdrmservices.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.jdrmservices.model.ItemCompraDireta;

@Component
@SessionScope
public class TabelaItensCompraDireta {

	private List<ItemCompraDireta> itensCompraDireta = new ArrayList<>();
	
	public BigDecimal getValorTotal() {
		return this.itensCompraDireta.stream()
				.map(ItemCompraDireta::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItens(List<ItemCompraDireta> itens) {
		this.itensCompraDireta = itens;
	}
	
	public void adicionarItem(ItemCompraDireta itemCompraDireta) {
		this.itensCompraDireta.add(itemCompraDireta);
	}
	
	public void removerItem(int index) {
		this.itensCompraDireta.remove(index);
	}
	
	public int getTotal() {
		return this.itensCompraDireta.size();
	}

	public List<ItemCompraDireta> getItens() {
		return this.itensCompraDireta;
	}
	
}
