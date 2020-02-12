var Sistema = Sistema || {};

Sistema.MenuPrincipal = (function() {
	
	function MenuPrincipal() {
		this.menu = $('ul li a');
	}
	
	MenuPrincipal.prototype.start = function() {
		this.menu.on('click', selecionarMenuItem.bind(this));
	}
	
	function selecionarMenuItem(event) {
		console.log(event.target);
	}
	
	return MenuPrincipal;
	
}());

$(function() {
	let menuPrincipal = new Sistema.MenuPrincipal();
	menuPrincipal.start();
});