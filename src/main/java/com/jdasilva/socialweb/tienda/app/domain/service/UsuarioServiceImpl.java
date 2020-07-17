package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jdasilva.socialweb.commons.models.entity.Usuario;

@Service("usuarioRestServiceTienda")
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	RestTemplate clienteRest;

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {

		Map<String, Long> pathVariables = new HashMap<>();
		pathVariables.put("id", id);

		// Usuario usuario =
		// clienteRest.getForObject("http://localhost:8001/api/usuarios/{id}",
		// Usuario.class, pathVariables);
//		Usuario usuario = clienteRest.getForObject("http://socialweb-usuarios/usuarios/{id}", Usuario.class,
//				pathVariables);
		Usuario usuario = clienteRest.getForObject("https://soyjose-usuarios.herokuapp.com/usuarios/{id}",
				Usuario.class, pathVariables);

		return usuario;

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("username", username);

		Usuario usuario = clienteRest.getForObject(
				"https://soyjose-usuarios.herokuapp.com/usuarios/username/{username}", Usuario.class, pathVariables);

		return usuario;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findByUsernameOptional(String username) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("username", username);

		Usuario usuario = clienteRest.getForObject(
				"https://soyjose-usuarios.herokuapp.com/usuarios/username/{username}", Usuario.class, pathVariables);

		return Optional.ofNullable(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public LinkedHashMap<String, LinkedHashMap<String, ?>> findAll() {

		LinkedHashMap<String, LinkedHashMap<String, ?>> usuarios = clienteRest
				.getForObject("https://soyjose-usuarios.herokuapp.com/usuarios/", LinkedHashMap.class);

		return usuarios;
	}

}
