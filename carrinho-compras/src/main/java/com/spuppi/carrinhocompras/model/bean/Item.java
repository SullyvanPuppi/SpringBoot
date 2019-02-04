package com.spuppi.carrinhocompras.model.bean;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;
    private BigDecimal valorTotal;
	
    /**
     * Construtor da classe Item.
     * 
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public Item(Produto produto, BigDecimal valorUnitario, int quantidade){
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.valorTotal = new BigDecimal(quantidade).multiply(valorUnitario);
	}
	/**
     * Retorna o produto.
     *
     * @return Produto
     */
	public Produto getProduto() {
		return produto;
	}
	/**
	 * Ajusta o produto.
	 * 
	 * @param produto
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	/**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	/**
	 * Ajusta o valor unitário do produto.
	 * 
	 * @param valorUnitario 
	 */
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	/**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * Ajusta a quantidade do mesmo produto.
	 * 
	 * @param codigo 
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
    	return valorTotal;
    }
    /**
	 * Ajusta o valor total do item.
	 * 
	 * @param codigo
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produto: ["+ produto + ", valorUnitario=" + valorUnitario + ", quantidade="
				+ quantidade + ", valorTotal do item=" + valorTotal + "]";
	}    
}