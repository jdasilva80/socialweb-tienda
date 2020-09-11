package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import com.jdasilva.socialweb.tienda.app.domain.relational.model.ItemPedido;

public interface ItemPedidoService {

	public List<ItemPedido> findAll();

	public Iterable<ItemPedido> save(Iterable<ItemPedido> pedidos);

	public ItemPedido save(ItemPedido pedido);
}
