package com.jdasilva.socialweb.tienda.app.clientrest;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;

@FeignClient(name = "socialweb-usuarios")
public interface UsuariosClienteRestFeign {

	@GetMapping("/usuarios/search/buscar-nombre")
	public Usuario findByUserName(@RequestParam String username);
	
//	@GetMapping("/usuarios/search/buscar-todos")
//	public LinkedHashMap buscarTodos();
	
	@GetMapping("/usuarios/{id}")
	public Usuario findUsuario(@PathVariable Long id);

	@GetMapping("/usuarios/")
	public LinkedHashMap<String, LinkedHashMap<String, ?>> findAll();
	
	@GetMapping("/usuarios/all")
	public List<Usuario> findAllUsuarios();
}
