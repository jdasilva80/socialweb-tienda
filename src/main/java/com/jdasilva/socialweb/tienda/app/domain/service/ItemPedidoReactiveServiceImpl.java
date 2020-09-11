package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.tienda.app.domain.document.dao.ItemPedidoReactiveDao;
import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;

@Service
public class ItemPedidoReactiveServiceImpl implements ItemPedidoReactiveService {

	@Autowired
	ItemPedidoReactiveDao itemPedidoDao;

	@Transactional(readOnly = true)
	@Override
	public List<ItemPedido> findAll() {

		return itemPedidoDao.findAll();
	}

	@Transactional
	@Override
	public Iterable<ItemPedido> insert(Iterable<ItemPedido> pedidos) {

		return itemPedidoDao.insert(pedidos);

	}

	@Transactional
	@Override
	public ItemPedido insert(ItemPedido pedido) {

		return itemPedidoDao.insert(pedido);
	}
}
