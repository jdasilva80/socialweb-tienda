package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.clientrest.UsuariosClienteRestFeign;

@Service("usuarioFeingService")
public class UsuarioServiceFeign implements IUsuarioService {

	@Autowired(required = false)
	private UsuariosClienteRestFeign clienteRestFeign;

	@Override
	public Usuario findByUsername(String username) {

		return clienteRestFeign.findByUserName(username);
	}

	@Override
	public Optional<Usuario> findByUsernameOptional(String username) {

		return Optional.ofNullable(clienteRestFeign.findByUserName(username));
	}

	@Override
	public Usuario findById(Long id) {
		
		return clienteRestFeign.findUsuario(id);
	}

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, ?>> findAll() {
		
		return clienteRestFeign.findAll();
	}


}
