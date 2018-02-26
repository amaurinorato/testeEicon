package br.com.eicon.pedido.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement(name="pedido")  
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	private Long numeroControle;
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@Column(name="data_cadastro")
	private LocalDate dataCadastro;
	
	@NotEmpty
	@Column(name="nome_produto")
	private String nomeProduto;
	
	@NotNull
	private Double valor;
	
	private Integer quantidade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	@NotNull
	private Cliente cliente;
	
	@Column(name="valor_total", precision=12, scale=2)
	private Double valorTotal;

	public Long getNumeroControle() {
		return numeroControle;
	}

	public void setNumeroControle(Long numeroControle) {
		this.numeroControle = numeroControle;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroControle == null) ? 0 : numeroControle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (numeroControle == null) {
			if (other.numeroControle != null)
				return false;
		} else if (!numeroControle.equals(other.numeroControle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [numeroControle=" + numeroControle + ", dataCadastro=" + dataCadastro + ", nomeProduto=" + nomeProduto + ", valor=" + valor
				+ ", quantidade=" + quantidade + ", cliente=" + cliente + ", valorTotal=" + valorTotal + "]";
	}
}
