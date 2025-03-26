package br.com.sinerji.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 150)
	private String nome;
	
	private Date idade;
	
	@Column(length = 2)
	private String sexo;
	
	@OneToMany(mappedBy = "Pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	
	@Override
	public String toString() {
		return "Pessoa [" + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (idade != null ? "idade=" + idade + ", " : "") + (sexo != null ? "sexo=" + sexo + ", " : "")
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getIdade() {
		return idade;
	}
	public void setIdade(Date idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.enderecos = endereco;
	}
	
	
}
