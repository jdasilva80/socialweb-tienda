package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;
import java.util.Optional;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.relational.model.Pedido;

public interface PedidoService {

	public List<Pedido> findAll();
	
	public Optional<Pedido> findById(Long id);
	
	public void delete(Pedido pedido);

	public List<Pedido> findByUsuario(Usuario usuario);

	public Iterable<Pedido> save(Iterable<Pedido> pedidos);
	
	public Pedido save(Pedido pedido);	

}
