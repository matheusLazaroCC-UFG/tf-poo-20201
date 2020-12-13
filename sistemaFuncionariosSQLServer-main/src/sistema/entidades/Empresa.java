package sistema.entidades;

import java.util.ArrayList;

public class Empresa {
	int id;
	String nome;
	String cnpj;
	String cep;
	String email;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return nome + " - " + cnpj;
	}
	
	public Funcionario consultarFuncionarios(int id) {
	
		return null;
	}
	
	public ArrayList<Funcionario> listarFuncionarios() {
		return null;
	}
	
	public void contratarFuncionarios(int id) {
	}
	
	public void demitirFuncionarios(int id) {
		//usar consultarFuncionario
		// fazer um throw especifico aqui para ver se conseguiu demitir ou não
	}
	
	public void editarFuncionarios(int id) {
		//usar consultarFuncionario
	}
}
