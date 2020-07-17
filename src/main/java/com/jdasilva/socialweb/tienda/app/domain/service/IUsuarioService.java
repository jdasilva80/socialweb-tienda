package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.LinkedHashMap;
import java.util.Optional;

import com.jdasilva.socialweb.commons.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findById(Long id);

	public Usuario findByUsername(String username);

	public Optional<Usuario> findByUsernameOptional(String username);

	public LinkedHashMap<String, LinkedHashMap<String, ?>> findAll();
}
