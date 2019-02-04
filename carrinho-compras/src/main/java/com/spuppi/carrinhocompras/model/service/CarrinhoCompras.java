package com.spuppi.carrinhocompras.model.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import com.spuppi.carrinhocompras.model.bean.Cliente;
import com.spuppi.carrinhocompras.model.bean.Item;
import com.spuppi.carrinhocompras.model.bean.Produto;


/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
	
	private ArrayList<Item> itens = new ArrayList<>();
	private BigDecimal valorTotal = new BigDecimal("0");
	private Cliente cliente;
	private long codCarrinho;
	
	public CarrinhoCompras() {
		 
	}
	public CarrinhoCompras(Cliente cliente) {
		 this.cliente = cliente;
	}
	/**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade){
    	try {
    		if(quantidade <= 0) {
        		throw new IllegalArgumentException("Quantidade menor que 1 ou negativo não é permitido!");
        	}
        	Item item = new Item(produto, valorUnitario, quantidade);
        	boolean adicionar = true;
        	for(Item itemCarrinho : itens) {
        		if(item.getProduto().getCodigo() == itemCarrinho.getProduto().getCodigo()) {
        			itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + quantidade);
        			itemCarrinho.setValorUnitario(valorUnitario);
        			itemCarrinho.setValorTotal(new BigDecimal(itemCarrinho.getQuantidade()).multiply(valorUnitario));
        			adicionar = false;
        			break;
        		}
        	}
    		if(adicionar) itens.add(item);	
    	}catch(IllegalArgumentException e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
    	for(Item item : itens) {
    		if(item.getProduto() == produto) {
    			itens.remove(item);
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
    	if(itens.remove(posicaoItem) == itens.get(posicaoItem)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
    	valorTotal = new BigDecimal("0");
    	for(Item itemCarrinho : itens) {    		
    		valorTotal = valorTotal.add(itemCarrinho.getValorTotal());
    	}
    	return valorTotal;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {    	
    	return itens;
    }
    
    /**
     * Retorna o cliente do carrinho de compras.
     *
     * @return Cliente
     */
    public Cliente getCliente() {    	
    	return cliente;
    }
    
    public long getCodCarrinho() {
    	this.codCarrinho = this.hashCode();
    	return codCarrinho;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString() {
		return "CarrinhoCompras " + this.hashCode() + " [" + cliente + ", itens=" + itens + ", valorTotal do Carrinho=" + getValorTotal() + "]";
	}
}