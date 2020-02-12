var Pdv = Pdv || {};

Pdv.AddItensContrato = (function() {
	
	function AddItensContrato() {
		this.btnAdicionar = $('#btnAdicionar');
		this.btnRemover = $('#btnRemover');
		this.especificacao = $('#especificacao');
		this.marca = $('#marca');
		this.unidadeMedida = $('#unidadeMedida');
		this.quantidade = $('#quantidade');
		this.valorUnitario = $('#valorUnitario');
		this.total = $('#total');
	}
	
	AddItensContrato.prototype.start = function() {
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
		let marca = $(this.marca).val();
		let unidadeMedida = $(this.unidadeMedida).val();
		let quantidade = Number($(this.quantidade).val());
		let valorUnitario = parseFloat($(this.valorUnitario).val().replace(/[.]/g, '').replace(',', '.'));
		let total = parseFloat($(this.total).val().replace(/[.]/g, '').replace(',', '.'));
		
		let itemContrato = {
			'especificacao': especificacao,
			'marca': marca,
			'unidadeMedida': unidadeMedida,
			'quantidade': quantidade,
			'valorUnitario': valorUnitario
		}
		
		let retorno = $.ajax({
			url: '/itenscontrato',
			method: 'POST',
			data: itemContrato
		});
		
		retorno.done(function(response) {
			$(this.especificacao).val('');
			$(this.marca).val('');
			$(this.unidadeMedida).val('');
			$(this.quantidade).val('');
			$(this.valorUnitario).val('');
			$(this.total).val('');
			
			$('#itensContratos').html(response);
		});
		
		retorno.fail(function(error) {
			console.log(error);
		});
	}
	
	function removerItem(e) {
		e.preventDefault();
		
		console.log(e.target);
		
		/*
		let retorno = $.ajax({
			url: '/itenscontrato/' + index,
			method: 'POST'
		});*/
	}
	
	return AddItensContrato;
	
}());

$(function() {
	let addItensContrato = new Pdv.AddItensContrato();
	addItensContrato.start();
});