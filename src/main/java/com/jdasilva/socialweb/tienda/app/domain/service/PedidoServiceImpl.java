package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.relational.dao.PedidoDao;
import com.jdasilva.socialweb.tienda.app.domain.relational.model.Pedido;


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
	public Iterable<Pedido> save(Iterable<Pedido> pedidos) {

		return pedidoDao.saveAll(pedidos);
	}

	@Transactional
	@Override
	public Pedido save(Pedido pedido) {
		
		return pedidoDao.save(pedido);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pedido> findByUsuario(Usuario usuario) {
		
		return pedidoDao.findByUsuarioId(usuario.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pedido> findById(Long id) {
		
		return pedidoDao.findById(id);		
	}

	@Transactional
	@Override
	public void delete(Pedido pedido) {
		
		pedidoDao.delete(pedido);
		
	}

}
