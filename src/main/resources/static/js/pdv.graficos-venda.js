/*
var Pdv = Pdv || {};

Pdv.Graficos = (function() {
	
	function Graficos() {		
		this.ctxGrafico = $('#grafico')[0].getContext('2d');
	}
	
	Graficos.prototype.start = function() {		
		buscarDadosDosGraficos.call(this);
	}
	
	function gerarCores(bar) {
		var cor = [];
		
		for(i = 0; i < bar.length; i++) {
			cor.unshift('rgba(' + Math.floor(Math.random() * 255) + ',' 
					+ Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ', 0.5)');
		}
		
		return cor;
	}
	
	function gerarCoresText(bar) {
		var cor = [];
		
		for(i = 0; i < bar.length; i++) {
			cor.unshift('rgba(' + Math.floor(Math.random() * 255) + ',' 
					+ Math.floor(Math.random() * 255) + ',' + Math.floor(Math.random() * 255) + ', 1.0)');
		}
		
		return cor;
	}
	
	function alterarTipoGrafico(e) {
		this.tipoGrafico = e.currentTarget.id;
		buscarDadosDosGraficos.call(this);
	}
	
	function buscarDadosDosGraficos() {
		$.ajax({
			url: 'vendas/graficosUrl',
			method: 'GET',
			success: rederizaGraficoTotalVendasDia.bind(this),
			error: rederizarGraficoError.bind(this)
		});
	}
	
	function rederizaGraficoTotalVendasDia(totalVendasDia) {
		var dia = [];
		var total = [];
		
		totalVendasDia.forEach(function(data) {
			dia.unshift(data.dia);
			total.unshift(data.total);
		});
		
		var graficoTotalVendasDia = new Chart(this.ctxTotalVendasDia, {
			type: 'line',
			data: {
				labels: dia,
				datasets: [{
					label: 'Total de vendas por dia',
					backgroundColor: gerarCores(dia),
					pointBorderColor: gerarCoresText(dia),
					pointBackgroundColor: '#fff',
					data: total
				}]
			}
		});
	}

	function rederizarGraficoError(error) {
		console.log(error);
	}
	
	return Graficos;
	
}());

$(function() {
	let graficos = new Pdv.Graficos();
	graficos.start();
});
*/