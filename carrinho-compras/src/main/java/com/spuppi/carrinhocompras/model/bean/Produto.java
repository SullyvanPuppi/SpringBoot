package com.spuppi.carrinhocompras.model.bean;

public class Produto {

    private Long codigo;
    private String descricao;

    /**
     * Construtor da classe Produto.
     *
     * @param codigo
     * @param descricao
     */
    public Produto(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
     * Retorna o código da produto.
     *
     * @return Long
     */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * Ajusta o código do produto.
	 * 
	 * @param codigo 
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
     * Retorna a descrição do produto.
     *
     * @return String
     */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Ajusta a descrição do produto.
	 * 
	 * @param descricao 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "codigo=" + codigo + ", descricao=" + descricao;
	}	
}