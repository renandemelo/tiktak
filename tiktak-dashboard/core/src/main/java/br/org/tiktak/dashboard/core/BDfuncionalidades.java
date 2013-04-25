package br.org.tiktak.dashboard.core;

import java.io.Serializable;

public class BDfuncionalidades implements Serializable {
	private String sistema;
	private String funcionalidade;
	private int quantidade;
	private String porcentagemFormatada;
	
	
	public BDfuncionalidades(/*String sistema, */String funcionalidade, int quantidade, String porcentagemFormatada) {
		//Depende da visualização estar modificada pra adicionar o sistema conforme necessário
		//this.sistema = sistema;
		this.funcionalidade = funcionalidade;
		this.quantidade = quantidade;
		this.porcentagemFormatada = porcentagemFormatada; 
	}
	
	public String getSistema() {
		return sistema;
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
