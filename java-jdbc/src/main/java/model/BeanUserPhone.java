package model;

public class BeanUserPhone {
	
	private String nome;
	private String number;
	private String email;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "BeanUserFone [nome=" + nome + ", number=" + number + ", email="
				+ email + "]";
	}
}
