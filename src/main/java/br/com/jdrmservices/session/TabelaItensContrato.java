package br.com.jdrmservices.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.jdrmservices.model.ItemContrato;

@Component
@SessionScope
public class TabelaItensContrato {

	private List<ItemContrato> itensContrato = new ArrayList<>();
	
	public BigDecimal getValorTotal() {
		return itensContrato.stream()
				.map(ItemContrato::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(ItemContrato item) {
		itensContrato.add(item);
	}
	
	public void removerItem(int index) {
		itensContrato.remove(index);
	}
	
	public int getTotal() {
		return itensContrato.size();
	}

	public List<ItemContrato> getItens() {
		return itensContrato;
	}
}