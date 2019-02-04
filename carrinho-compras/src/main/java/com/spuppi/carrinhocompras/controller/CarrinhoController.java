package com.spuppi.carrinhocompras.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spuppi.carrinhocompras.model.bean.Produto;
import com.spuppi.carrinhocompras.model.service.CarrinhoComprasFactory;

@Controller
public class CarrinhoController {
	
	/*
	 * Variáveis:
	 * carrinhoFactory
	 * produtos
	 * carrinhos
	 * items
	 * 
	 * criadas sem persistência por critério exigido
	 * */
	CarrinhoComprasFactory carrinhoFactory = new CarrinhoComprasFactory();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	ModelAndView mv = new ModelAndView("index");
	
	@RequestMapping("/")
	public ModelAndView index() {
		return mv;
	}
	
	@RequestMapping("/resetarSistema")
	public String resetar() {
		mv = new ModelAndView("index");
		produtos.removeAll(produtos);
		carrinhoFactory.getCarrinhos().clear();
		return "index";
	}	
	
	@RequestMapping(value="/cadastrarProduto",  method=RequestMethod.POST)
	public ModelAndView cadastrarProduto(String nomeProduto) {
		long codigoProduto = produtos.size()+1;
		Produto produtoAdd = new Produto(codigoProduto, nomeProduto);
		produtos.add(produtoAdd);
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@RequestMapping(value="/criarCarrinho", method=RequestMethod.GET)
	public ModelAndView criarCarrinho() {
		carrinhoFactory.criar(String.valueOf(carrinhoFactory.getCarrinhos().size()+1));
		mv.addObject("carrinhos", carrinhoFactory.getCarrinhos());
		return mv;
	}
	
	@RequestMapping(value="/removerCarrinho", method=RequestMethod.POST)
	public ModelAndView removerCarrinho(String identificacaoCliente) {
		carrinhoFactory.invalidar(identificacaoCliente);
		mv.addObject("carrinhos", carrinhoFactory.getCarrinhos());		
		return mv;
	}
	
	@RequestMapping(value="/adicionarItem", method=RequestMethod.POST)
	public ModelAndView adicionarItem(String identificacaoCliente, int codProduto, BigDecimal valorUnitario, int quantidade) {
		carrinhoFactory.criar(identificacaoCliente).adicionarItem(produtos.get(codProduto-1), valorUnitario, quantidade);
		mv.addObject("carrinhos", carrinhoFactory.getCarrinhos());
		return mv;
	}
	
	@RequestMapping(value="/removerItem", method=RequestMethod.POST)
	public ModelAndView removerItem(String identificacaoCliente, int codProduto) {
		carrinhoFactory.criar(identificacaoCliente).removerItem(produtos.get(codProduto-1));
		mv.addObject("carrinhos", carrinhoFactory.getCarrinhos());	
		return mv;
	}
}
