package com.jdasilva.socialweb.tienda.app.domain.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jdasilva.socialweb.tienda.app.domain.model.ItemPedido;

public interface ItemPedidoDao extends MongoRepository<ItemPedido, String> {

}
