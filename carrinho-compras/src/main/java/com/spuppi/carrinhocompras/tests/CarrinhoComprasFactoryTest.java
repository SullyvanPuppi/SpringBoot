package com.spuppi.carrinhocompras.tests;

import java.math.BigDecimal;

import com.spuppi.carrinhocompras.model.bean.Produto;
import com.spuppi.carrinhocompras.model.service.CarrinhoComprasFactory;


public class CarrinhoComprasFactoryTest {
	
	public static void main(String[] args) {
		CarrinhoComprasFactory carrinhoFactory = new CarrinhoComprasFactory();
		
		//---ITENS DE TESTE		
		Produto produto1 = new Produto(1L, "Cadeira de rodas");
		Produto produto2 = new Produto(2L, "Microcomputador");
		Produto produto3 = new Produto(3L, "Bola de futebol");
		Produto produto4 = new Produto(4L, "Paraquedas");
		//---FIM DE ITENS DE TESTE
		
		System.out.println("CRIAR E ADICIONAR 1 ITEM EM CADA CARRINHO:");
		try {
			carrinhoFactory.criar("1").adicionarItem(produto1, new BigDecimal("110"), 1);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			carrinhoFactory.criar("2").adicionarItem(produto1, new BigDecimal("180"), 2);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			carrinhoFactory.criar("3").adicionarItem(produto3, new BigDecimal("250"), 1);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			carrinhoFactory.criar("43").adicionarItem(produto2, new BigDecimal("550"), 1);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			carrinhoFactory.criar("58").adicionarItem(produto4, new BigDecimal("250"), 1);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("LISTAR TODOS OS CARRINHOS E CLIENTES RESPECTIVOS");
				
		for(Object carrinho : carrinhoFactory.getCarrinhos().toArray()) {
			System.out.println(carrinho);
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("TRATAR EXCEÇÕES\n");
		
		try {
			carrinhoFactory.criar("21").adicionarItem(produto1, new BigDecimal("asd"), 1);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			carrinhoFactory.criar("12").adicionarItem(produto1, new BigDecimal("120"), -2);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("ADICIONAR UM NOVO PRODUTO NO CARRINHO DO CLIENTE 43");
		
		try {
			carrinhoFactory.criar("43").adicionarItem(produto1, new BigDecimal("350"), 2);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("ADICIONAR UM NOVO PRODUTO REPETIDO NO CARRINHO DO CLIENTE 58 COM QUANTIDADE E VALOR UNITÁRIO DIFERENTE");
		
		try {
			carrinhoFactory.criar("58").adicionarItem(produto4, new BigDecimal("75"), 5);	
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("CRIAR UM NOVO CARRINHO PARA O CLIENTE 2, COMO JÁ EXISTE RETORNAR O CARRINHO ATUAL");
		
		System.out.println(carrinhoFactory.criar("2"));
		
		System.out.println("-------------------------------\n");
		System.out.println("REMOVER O CARRINHO DO CLIENTE 3");
		
		System.out.println(carrinhoFactory.invalidar("3"));
		
		System.out.println("-------------------------------\n");
		System.out.println("LISTAR TODOS OS CARRINHOS E CLIENTES RESPECTIVOS");
		
		for(Object carrinho : carrinhoFactory.getCarrinhos().toArray()) {
			System.out.println(carrinho);
		}
		
		System.out.println("-------------------------------\n");
		System.out.println("TICKET MÉDIO DOS CARRINHOS ATUAIS");
		
		System.out.println("Ticket médio dos carrinhos: " + carrinhoFactory.getValorTicketMedio());
		
	}
}