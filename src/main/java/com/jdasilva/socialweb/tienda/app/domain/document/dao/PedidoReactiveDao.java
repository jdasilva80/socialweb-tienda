package com.jdasilva.socialweb.tienda.app.domain.document.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;

public interface PedidoReactiveDao extends MongoRepository<Pedido, String> {

	public List<Pedido> findByUsuario(Usuario usuario);

}
