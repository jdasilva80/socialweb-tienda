package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;

public interface ItemPedidoReactiveService {

	public List<ItemPedido> findAll();

	public Iterable<ItemPedido> insert(Iterable<ItemPedido> pedidos);

	public ItemPedido insert(ItemPedido pedido);
}
