/* mascaras.js */
// Refatorar este javascript para remover duplicidade
var Pdv = Pdv || {};

Pdv.Mascaras = (function() {
	
	function Mascaras() {
		this.moeda = $(".moeda-mask");
		this.date = $(".date-mask");
		this.cpf = $(".cpf-mask");
		this.cnpj = $(".cnpj-mask");
		this.celular = $(".celular-mask");
		this.fixo = $(".fixo-mask");
		this.telefone = $('.telefone-mask');
		this.quantidade = $('.quantidade-mask');
		this.cpfCnpj = $('.cpfCnpj-mask');
		this.tipoPessoa = $('#tipoPessoa');
	}
	
	Mascaras.prototype.start = function() {		
		this.moeda.maskMoney({
			prefix:'', //R$
			allowNegative: true, 
			thousands:'.', 
			decimal:',', 
			affixesStay: false
		});

		this.date.mask("00/00/0000", {placeholder: "__/__/____"});
		this.cpf.mask("000.000.000-00", {placeholder: "___.___.___-__"});
		this.cnpj.mask("00.000.000/0000-00", {placeholder: "__.___.___/____-__"});
		this.celular.mask("(00) 00000-0000", {placeholder: "(99) 99999-9999"});
		this.fixo.mask("(00) 0000-0000", {placeholder: "(99) 9999-9999"});
		
		this.quantidade.maskMoney({
			prefix: '', 
			precision: 3,
			allowNegative: true, 
			thousands:'.', 
			decimal:',', 
			affixesStay: false
		});
		
		var maskBehavior = function(tel) {
			return tel.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		}
		
		var options = {
			onKeyPress: function(tel, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}
		}

		this.telefone.mask(maskBehavior, options);
		
		var maskBehaviorCpfCnpj = function(val) {
			return val.replace(/\D/g, '').length === 14 ? '00.000.000/0000-00' : '000.000.000-00999';
		}
		
		var optionsCpfCnpj = {
			onKeyPress: function(val, e, field, options) {
				field.mask(maskBehaviorCpfCnpj.apply({}, arguments), optionsCpfCnpj);
			}
		}

		this.cpfCnpj.mask(maskBehaviorCpfCnpj, optionsCpfCnpj);
	}
	
	return Mascaras;
	
}());

$(function() {
	$('.datepicker').datepicker({
		format: 'dd/mm/yyyy',
		language: 'pt-BR'
	});
	
	var mascaras = new Pdv.Mascaras();
	mascaras.start();
});