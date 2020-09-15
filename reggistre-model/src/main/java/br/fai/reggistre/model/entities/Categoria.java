package br.fai.reggistre.model.entities;

public class Categoria extends BasePojo{
	
	private String nome;
	private String tipo;
	private int icone;
	private Long pessoaFisicaId;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getIcone() {
		return icone;
	}
	public void setIcone(int icone) {
		this.icone = icone;
	}
	public Long getPessoaFisicaId() {
		return pessoaFisicaId;
	}
	public void setPessoaFisicaId(Long pessoaFisicaId) {
		this.pessoaFisicaId = pessoaFisicaId;
	}
	
	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", tipo=" + tipo + ", icone=" + icone + ", pessoaFisicaId= " + pessoaFisicaId + "]";
	}

}
