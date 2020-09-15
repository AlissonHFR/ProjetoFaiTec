package br.fai.reggistre.model.entities;

public class PessoaFisica extends BasePojo{
	
	
	private String nomeUsuario;
	private String nomeCompleto;
	private String email;
	private String emailAlternativo;
	private String senha;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailAlternativo() {
		return emailAlternativo;
	}
	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	@Override
	public String toString() {
		return "PessoaFisica [nomeUsuario=" + nomeUsuario + ", nomeCompleto=" + nomeCompleto + ", email=" + email + ", emailAlternativo=" + emailAlternativo + ", senha=" + senha + "]";
	}

}
