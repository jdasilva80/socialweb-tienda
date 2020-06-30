package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.commons.models.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.dao.PedidoDao;
import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	PedidoDao pedidoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Pedido> findAll() {

		return pedidoDao.findAll();
	}

	@Transactional
	@Override
	public Iterable<Pedido> insert(Iterable<Pedido> pedidos) {

		return pedidoDao.insert(pedidos);

	}

	@Transactional
	@Override
	public Pedido insert(Pedido pedido) {
		
		return pedidoDao.insert(pedido);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pedido> findByUsuario(Usuario usuario) {
		
		return pedidoDao.findByUsuario(usuario);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pedido> findById(String id) {
		
		return pedidoDao.findById(id);
	}

	@Override
	public void delete(Pedido pedido) {
		
		pedidoDao.delete(pedido);
		
	}

	@Transactional
	@Override
	public Pedido save(Pedido pedido) {
		
		return pedidoDao.save(pedido);
	}

}
