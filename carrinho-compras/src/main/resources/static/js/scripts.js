var query = location.search.slice(1);
var partes = query.split('&');
var get = {};
partes.forEach(function (parte) {
    var chaveValor = parte.split('=');
    var chave = chaveValor[0];
    var valor = chaveValor[1];
    get[chave] = valor;
});

jQuery('.collection > a').click(function(){
	jQuery('.collection .collection').slideUp();
	if(jQuery(this).hasClass('active')){
		jQuery(this).removeClass('active');
		jQuery(this).closest('.collection').find('.collection').slideUp();
	}else{
		jQuery('.collection a').removeClass('active');
		jQuery(this).addClass('active');
		jQuery(this).closest('.collection').find('.collection').slideDown();
	}
});

jQuery('#btnNovoProduto').click(function(){
	jQuery('#formProduto').toggle('slow');
})

jQuery('a.addItemCarrinho').click(function(){
	var url = "/carrinho-compras/adicionarItem";
	dados = jQuery(this).closest('div.card').find('div.card-content');
	var carrinho = jQuery('a.collection-item.active').find('span.codCarrinho').html();
	var identificacaoCliente = jQuery('a.collection-item.active').find('span.identificacaoCliente').html();
	var codProduto = jQuery(dados).find('span.codProduto').html();
	var valorUnitario = jQuery(dados).find('input.valorUnitario').val();
	var quantidade = jQuery(dados).find('input.quantidade').val();
	if(carrinho == null){
		alert('Para adicionar um produto primeiro selecione um carrinho.');
	}else if(valorUnitario == "" || quantidade == ""){
		alert('Verifique os dados do produto desejado.');
	}else{
		jQuery.ajax({
			  method: "POST",
		  url: "/carrinho-compras/adicionarItem",
		  data: { identificacaoCliente : identificacaoCliente.trim(), codProduto : codProduto, valorUnitario : valorUnitario, quantidade : quantidade}
		})
		.done(function() {
			window.location.replace('/carrinho-compras/?carrinho=' + carrinho.trim());
		})	
	}	
});

jQuery('.collection-item .deletarCarrinho').click(function(){
	var url = "/carrinho-compras/removerCarrinho";
	var identificacaoCliente = jQuery(this).closest('a').find('span.identificacaoCliente').html();
	jQuery.ajax({
		method: "POST",
		url: "/carrinho-compras/removerCarrinho",
		data: { identificacaoCliente : identificacaoCliente.trim()}
	})
	.done(function() {
		window.location.replace('/carrinho-compras');
	})
})

jQuery('.collection-item .deletarItem').click(function(){
	var url = "/carrinho-compras/removerItem";
	var carrinho = jQuery(this).closest('div.collection.carrinho').find('a.active').attr('id');
	var identificacaoCliente = jQuery(this).closest('div.collection.carrinho').find('a.active').attr('codcliente');
	var codProduto = jQuery(this).attr('codproduto');
	jQuery.ajax({
		method: "POST",
		url: "/carrinho-compras/removerItem",
		data: { identificacaoCliente : identificacaoCliente, codProduto : codProduto}
	})
	.done(function() {
		window.location.replace('/carrinho-compras/?carrinho=' + carrinho.trim());
	})
})

if(get['carrinho'] !== null){
	jQuery("#"+get['carrinho']).trigger('click');
}