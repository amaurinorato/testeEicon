package br.com.eicon.pedido.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eicon.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findByDataCadastro(LocalDate dataCadastro);

}
