package br.com.sinerji.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 100)
	private String cidade;
	
	@Column(length = 2)
	private String estado;
	
	@Column(length = 100)
	private String logradouro;
	
	@Column(length = 8)
	private String cep;
	
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id") 
	private Pessoa Pessoa;
	
	@Override
	public String toString() {
		return "Endereco [" + (id != null ? "id=" + id + ", " : "") + (cidade != null ? "cidade=" + cidade + ", " : "")
				+ (estado != null ? "estado=" + estado + ", " : "")
				+ (logradouro != null ? "logradouro=" + logradouro + ", " : "")
				+ (cep != null ? "cep=" + cep + ", " : "") + (numero != null ? "numero=" + numero : "") + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
	
	public Pessoa getPessoa() {
		return Pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		Pessoa = pessoa;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
