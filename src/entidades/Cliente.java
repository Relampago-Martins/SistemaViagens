package entidades;

import java.io.Serializable;

public class Cliente implements Serializable{
	private String nome, cpf;

	public Cliente(String nome, String cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
