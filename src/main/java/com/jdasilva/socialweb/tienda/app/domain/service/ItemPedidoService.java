package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import com.jdasilva.socialweb.tienda.app.domain.model.ItemPedido;

public interface ItemPedidoService {

	public List<ItemPedido> findAll();

	public Iterable<ItemPedido> insert(Iterable<ItemPedido> pedidos);

	public ItemPedido insert(ItemPedido pedido);
}
