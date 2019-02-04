package com.spuppi.carrinhocompras.model.bean;

public class Cliente {
	
	private String identificacaoCliente;

	
	/**
	 * Construtor da classe Cliente
	 * 
	 * @param identificacaoCliente
	 */
	public Cliente(String identificacaoCliente) {
		this.identificacaoCliente = identificacaoCliente;
	}
	/**
	 * Retorna a identificação do Cliente
	 * 
	 * @return String
	 */
	public String getIdentificacaoCliente() {
		return identificacaoCliente;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente: [identificacaoCliente=" + identificacaoCliente + "]";
	}
}