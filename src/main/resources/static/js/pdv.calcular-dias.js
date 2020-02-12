var Pdv = Pdv || {};

Pdv.CalcularDias = (function() {
	
	function CalcularDias() {
		this.dataInicio = $('#dataInicio');
		this.dataTermino = $('#dataTermino');
		this.prazo = $('#prazo');
	}
	
	CalcularDias.prototype.start = function() {
		$(this.prazo).on('focus', calcularIntervalorEmDias.bind(this));
	}
	
	function calcularIntervalorEmDias() {	
		let dataSeparadaInicio = $(this.dataInicio).val().split('/');
		let dataSeparadaTermino = $(this.dataTermino).val().split('/');
		
		let diaInicio = dataSeparadaInicio[0];
		let mesInicio = dataSeparadaInicio[1];
		let anoInicio = dataSeparadaInicio[2];
		
		let diaTermino = dataSeparadaTermino[0];
		let mesTermino = dataSeparadaTermino[1];
		let anoTermino = dataSeparadaTermino[2];
		
		let dataInicio = new Date(mesInicio + '/' + diaInicio + '/' + anoInicio);
		let dataTermino = new Date(mesTermino + '/' + diaTermino + '/' + anoTermino);
		
		let diferencaHoras = Math.abs(dataTermino.getTime() - dataInicio.getTime());
		let diferencaDias = Math.ceil(diferencaHoras / (1000 * 3600 * 24)); 
		
		$(this.prazo).val(diferencaDias);
	}
	
	return CalcularDias;
	
}());

$(function() {
	let calcularDias = new Pdv.CalcularDias();
	calcularDias.start();
});