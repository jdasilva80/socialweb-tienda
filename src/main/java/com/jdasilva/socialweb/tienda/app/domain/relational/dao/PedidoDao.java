package com.jdasilva.socialweb.tienda.app.domain.relational.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdasilva.socialweb.tienda.app.domain.relational.model.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Long> {

	public List<Pedido> findByUsuarioId(Long usuarioId);

}
