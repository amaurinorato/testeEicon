package br.com.eicon.pedido.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pedidos")   
@XmlAccessorType(XmlAccessType.FIELD) 
public class PedidoWrapper {
	
	private List<Pedido> pedido;

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}
	
}
