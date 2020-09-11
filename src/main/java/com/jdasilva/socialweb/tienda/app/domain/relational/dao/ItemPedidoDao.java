package com.jdasilva.socialweb.tienda.app.domain.relational.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdasilva.socialweb.tienda.app.domain.relational.model.ItemPedido;


public interface ItemPedidoDao extends JpaRepository<ItemPedido, Long> {

}
