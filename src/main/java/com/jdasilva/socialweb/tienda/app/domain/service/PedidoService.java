package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;
import java.util.Optional;

import com.jdasilva.socialweb.commons.models.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;

public interface PedidoService {

	public List<Pedido> findAll();
	
	public Optional<Pedido> findById(String id);
	
	public void delete(Pedido pedido);

	public List<Pedido> findByUsuario(Usuario usuario);

	public Iterable<Pedido> insert(Iterable<Pedido> pedidos);
	
	public Pedido save(Pedido pedidos);	

	public Pedido insert(Pedido pedido);
}
