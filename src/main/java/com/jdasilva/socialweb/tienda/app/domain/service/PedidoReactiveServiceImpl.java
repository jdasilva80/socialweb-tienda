package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.document.dao.PedidoReactiveDao;
import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;


@Service
public class PedidoReactiveServiceImpl implements PedidoReactiveService {

	@Autowired
	PedidoReactiveDao pedidoDao;

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

	@Transactional
	@Override
	public void delete(Pedido pedido) {
		
		pedidoDao.delete(pedido);
		
	}

}
