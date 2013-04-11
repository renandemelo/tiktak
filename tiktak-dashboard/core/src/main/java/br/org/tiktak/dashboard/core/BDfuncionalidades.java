package br.org.tiktak.dashboard.core;

public class BDfuncionalidades {
	private String funcionalidade;
	private int quantidade;
	private double porcentagem;
	
	public BDfuncionalidades(String funcionalidade, int quantidade, int porcentagem) {
		this.funcionalidade = funcionalidade;
		this.quantidade = quantidade;
		this.porcentagem = porcentagem; 
	}
	
	public double getPorcetagem() {
		return porcentagem;
	}

	public void setPorcetagem(double porcetagem) {
		this.porcentagem = porcetagem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

}
