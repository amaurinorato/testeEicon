package br.com.eicon.pedido.resource;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eicon.pedido.business.PedidoBO;
import br.com.eicon.pedido.exceptions.BusinessException;
import br.com.eicon.pedido.model.PedidoWrapper;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoBO bo;
	
	@PostMapping(consumes={"application/json", "application/xml"})
	public ResponseEntity<?> salvarPedidos(@RequestBody @Valid PedidoWrapper pedidos) {
		try {
			bo.salvarPedidos(pedidos.getPedido());
			return ResponseEntity.status(HttpStatus.CREATED).body("Pedidos criados com sucesso");
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(consumes={"application/json", "application/xml"})
	public ResponseEntity<?> buscarPedidos(@RequestParam(value="numeroControle", required=false) Long numeroControle, @RequestParam(value="data", required=false) @DateTimeFormat(iso = ISO.DATE)LocalDate data) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bo.listarPedidos(numeroControle, data));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
