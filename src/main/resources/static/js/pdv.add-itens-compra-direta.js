var Pdv = Pdv || {};

Pdv.AddItensCompraDireta = (function() {
	
	function AddItensCompraDireta() {
		this.btnAdicionar = $('#btnAdicionar');
		this.btnRemover = $('#btnRemover');
		this.especificacao = $('#especificacao');
		this.unidadeMedida = $('#unidadeMedida');
		this.quantidade = $('#quantidade');
		this.valorUnitario = $('#valorUnitario');
		this.total = $('#total');
	}
	
	AddItensCompraDireta.prototype.start = function() {
		$(this.total).on('focus', calcularValorTotalItens.bind(this));
		$(this.btnAdicionar).on('click', adicionarItem.bind(this));
		$(this.btnRemover).on('click', removerItem.bind(this));
	}
	
	function calcularValorTotalItens(e) {
		e.preventDefault();
		
		let quantidade = parseFloat($(this.quantidade).val().replace(/[.]/g, '').replace(',', '.'));
		let valorUnitario = parseFloat($(this.valorUnitario).val().replace(/[.]/g, '').replace(',', '.'));
		let total = parseFloat(quantidade * valorUnitario).toFixed(2);
		
		$(this.total).val(total);
		
		$(this.total).val($(this.total).val().replace('.', ','));
	}
	
	function adicionarItem(e) {
		e.preventDefault();
		
		let especificacao = $(this.especificacao).val();
		let unidadeMedida = $(this.unidadeMedida).val();
		let quantidade = Number($(this.quantidade).val());
		let valorUnitario = parseFloat($(this.valorUnitario).val().replace(/[.]/g, '').replace(',', '.'));
		let total = parseFloat($(this.total).val().replace(/[.]/g, '').replace(',', '.'));
		
		let itemCompraDireta = {
			'especificacao': especificacao,
			'unidadeMedida': unidadeMedida,
			'quantidade': quantidade,
			'valorUnitario': valorUnitario
		}
		
		let retorno = $.ajax({
			url: '/itenscompradireta',
			method: 'POST',
			data: itemCompraDireta
		});
		
		retorno.done(function(response) {
			$(this.especificacao).val('');
			$(this.unidadeMedida).val('');
			$(this.quantidade).val('');
			$(this.valorUnitario).val('');
			$(this.total).val('');
			
			$('#itensComprasDiretas').html(response);
		});
		
		retorno.fail(function(error) {
			console.log(error);
		});
	}
	
	function removerItem(e) {
		e.preventDefault();
		
		console.log(e);
		
		/*
		let retorno = $.ajax({
			url: '/itenscompraDireta/' + index,
			method: 'POST'
		});*/
	}
	
	return AddItensCompraDireta;
	
}());

$(function() {
	let addItensCompraDireta = new Pdv.AddItensCompraDireta();
	addItensCompraDireta.start();
});