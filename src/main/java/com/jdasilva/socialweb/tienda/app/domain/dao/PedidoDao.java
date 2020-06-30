package com.jdasilva.socialweb.tienda.app.domain.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jdasilva.socialweb.commons.models.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;

public interface PedidoDao extends MongoRepository<Pedido, String> {

	public List<Pedido> findByUsuario(Usuario usuario);

}
