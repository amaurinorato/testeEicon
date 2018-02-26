package br.com.eicon.pedido.business;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eicon.pedido.exceptions.BusinessException;
import br.com.eicon.pedido.model.Pedido;
import br.com.eicon.pedido.repository.PedidoRepository;

@Service
public class PedidoBO {
	
	private static final double CINCO_POR_CENTO = 0.05;
	private static final double DEZ_POR_CENTO = 0.1;
	private static final int LIMITE_PEDIDOS = 10;
	@Autowired
	PedidoRepository pedidoRepository;

	@Transactional(value=TxType.REQUIRES_NEW)
	public void salvarPedidos(List<Pedido> pedidos) throws BusinessException {
		if (pedidos.size() > LIMITE_PEDIDOS) {
			throw new BusinessException("Quantidade de pedidos maior do que a permitida!");
		} else {
			List<Pedido> pedidosExistentes = pedidoRepository.findAll();
			for (Pedido pedido : pedidos) {
				if (pedidosExistentes.contains(pedido)) {
					throw new BusinessException("Já existe um pedido cadastrado com o número " + pedido.getNumeroControle());
				}
				if (pedido.getDataCadastro() == null) {
					pedido.setDataCadastro(LocalDate.now());
				}
				calcularQuantidadeDesconto(pedido);
				pedidoRepository.save(pedido);
			}
		}
	}

	private void calcularQuantidadeDesconto(Pedido pedido) {
		if (pedido.getQuantidade() == null || Long.valueOf(0).equals(pedido.getQuantidade())) {
			pedido.setQuantidade(1);
		} else if (pedido.getQuantidade() > 10) {
			pedido.setValorTotal(pedido.getQuantidade() * pedido.getValor() - (pedido.getQuantidade() * pedido.getValor() * DEZ_POR_CENTO));
		} else if (pedido.getQuantidade() > 5) {
			pedido.setValorTotal(pedido.getQuantidade() * pedido.getValor() - (pedido.getQuantidade() * pedido.getValor() * CINCO_POR_CENTO));
		}
	}
	
	public List<Pedido> listarPedidos(Long numeroControle, LocalDate data) {
		if (data != null) {
			return pedidoRepository.findByDataCadastro(data);
		} else if (numeroControle != null) {
			return Arrays.asList(pedidoRepository.findOne(numeroControle));
		}
		return pedidoRepository.findAll();
	}
}
