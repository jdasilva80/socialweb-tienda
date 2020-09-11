package com.jdasilva.socialweb.tienda.app.domain.document.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;

public interface ItemPedidoReactiveDao extends MongoRepository<ItemPedido, String> {

}
