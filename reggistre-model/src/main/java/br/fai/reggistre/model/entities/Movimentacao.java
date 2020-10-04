package br.fai.reggistre.model.entities;

import java.sql.Date;

public class Movimentacao extends BasePojo {
	
	private String nome;
	private String descricao;
	private Date data;
	private String tipoMovimentacao;
	private double valor;
	private Long pessoaFisicaId;
	private Long categoriaId;
	
	private Categoria categoria;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getPessoaFisicaId() {
		return pessoaFisicaId;
	}
	public void setPessoaFisicaId(Long pessoaFisicaId) {
		this.pessoaFisicaId = pessoaFisicaId;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	@Override
	public String toString() {
		return "Movimentacao [nome=" + nome + ", descricao=" + descricao + ", data=" + data + ", tipoMovimentacao=" + tipoMovimentacao + ", valor=" + valor + ", pessoaFisicaId=" + pessoaFisicaId + ", categoriaId=" + categoriaId +"]";
	}
	

}
