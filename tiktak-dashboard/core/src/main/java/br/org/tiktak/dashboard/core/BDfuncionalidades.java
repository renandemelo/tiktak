package br.org.tiktak.dashboard.core;

import java.io.Serializable;

public class BDfuncionalidades implements Serializable {
	private String funcionalidade;
	private int quantidade;
	private String porcentagemFormatada;
	
	public BDfuncionalidades(String funcionalidade, int quantidade, String porcentagemFormatada) {
		this.funcionalidade = funcionalidade;
		this.quantidade = quantidade;
		this.porcentagemFormatada = porcentagemFormatada; 
	}
	
	public String getPorcetagem() {
		return porcentagemFormatada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

}
