package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.tienda.app.domain.dao.ItemPedidoDao;
import com.jdasilva.socialweb.tienda.app.domain.model.ItemPedido;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

	@Autowired
	ItemPedidoDao itemPedidoDao;

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
