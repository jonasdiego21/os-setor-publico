package br.com.jdrmservices.session;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.jdrmservices.model.ItemCompraDireta;
import br.com.jdrmservices.model.enumeration.UnidadeMedida;

public class TabelaItensCompraDiretaTest {

	private TabelaItensCompraDireta tabelaItensCompraDireta;
	
	@Before
	public void setUp() {
		this.tabelaItensCompraDireta = new TabelaItensCompraDireta();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensCompraDireta.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		ItemCompraDireta item = new ItemCompraDireta();
		
		item.setCodigo(1L);
		item.setEspecificacao("Camera fotográfica t4i");
		item.setQuantidade(1);
		item.setUnidadeMedida(UnidadeMedida.UN);
		item.setValorUnitario(new BigDecimal("1156.59"));
		
		this.tabelaItensCompraDireta.adicionarItem(item);
		
		assertEquals(new BigDecimal("1156.59"), this.tabelaItensCompraDireta.getValorTotal());		
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		ItemCompraDireta item1 = new ItemCompraDireta();
		ItemCompraDireta item2 = new ItemCompraDireta();
		ItemCompraDireta item3 = new ItemCompraDireta();
		
		item1.setCodigo(1L);
		item1.setEspecificacao("Camera fotográfica D4i");
		item1.setQuantidade(1);
		item1.setUnidadeMedida(UnidadeMedida.UN);
		item1.setValorUnitario(new BigDecimal("2000.00"));
		
		item2.setCodigo(2L);
		item2.setEspecificacao("Camera fotográfica t4i");
		item2.setQuantidade(1);
		item2.setUnidadeMedida(UnidadeMedida.UN);
		item2.setValorUnitario(new BigDecimal("1500.00"));
		
		item3.setCodigo(3L);
		item3.setEspecificacao("Camera fotográfica F4i");
		item3.setQuantidade(1);
		item3.setUnidadeMedida(UnidadeMedida.UN);
		item3.setValorUnitario(new BigDecimal("1500.00"));
		
		this.tabelaItensCompraDireta.adicionarItem(item1);
		this.tabelaItensCompraDireta.adicionarItem(item2);
		this.tabelaItensCompraDireta.adicionarItem(item3);
		
		assertEquals(new BigDecimal("5000.00"), this.tabelaItensCompraDireta.getValorTotal());		
	}
}
