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
		return itensCompraDireta.stream()
				.map(ItemCompraDireta::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(ItemCompraDireta itemCompraDireta) {
		itensCompraDireta.add(itemCompraDireta);
	}
	
	public void removerItem(int index) {
		itensCompraDireta.remove(index);
	}
	
	public int getTotal() {
		return itensCompraDireta.size();
	}

	public List<ItemCompraDireta> getItens() {
		return itensCompraDireta;
	}
	
}
