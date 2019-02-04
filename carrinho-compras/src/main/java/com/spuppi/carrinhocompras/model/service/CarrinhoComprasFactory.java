package com.spuppi.carrinhocompras.model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.spuppi.carrinhocompras.model.bean.Cliente;


/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {
	
	private ArrayList<CarrinhoCompras> carrinhos = new ArrayList<>();
	private static final Map<Cliente, CarrinhoCompras> carrinhosCache = new HashMap<>();
	
	public CarrinhoComprasFactory() {		
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
    	Cliente cliente = new Cliente(identificacaoCliente);
    	for(Entry<Cliente, CarrinhoCompras> entry : carrinhosCache.entrySet()) {
    		if(entry.getKey().getIdentificacaoCliente().equals(identificacaoCliente)) {
    			return entry.getValue();
    		}
    	}
    	CarrinhoCompras carrinho = new CarrinhoCompras(cliente);
    	carrinhosCache.put(cliente, carrinho);  	
    	return carrinhosCache.get(cliente);
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
    	BigDecimal totalCarrinhos = new BigDecimal("0");
    	for(CarrinhoCompras carrinho : carrinhosCache.values()) {
    		totalCarrinhos = totalCarrinhos.add(carrinho.getValorTotal());    		
    	}
    	BigDecimal valorMedio = (totalCarrinhos).divide(new BigDecimal(this.getCarrinhos().size()),2,RoundingMode.HALF_UP);
    	return valorMedio;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
    	for(Entry<Cliente, CarrinhoCompras> entry : carrinhosCache.entrySet()) {
    		if(entry.getKey().getIdentificacaoCliente().equals(identificacaoCliente)) {
    			carrinhosCache.remove(entry.getKey(),entry.getValue());
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Retorna a lista de carrinhos de compras.
     *
     * @return CarrinhoCompras
     */
    public Collection<CarrinhoCompras> getCarrinhos() {    	
//    	return carrinhos;
    	return carrinhosCache.values();
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString() {
		return "CarrinhoComprasFactory [carrinhos=" + carrinhos + "]";
	}
}