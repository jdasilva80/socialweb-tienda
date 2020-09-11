package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jdasilva.socialweb.commons.models.document.Producto;

@Service("productoReactiveRestServiceTienda")
public class ProductoReactiveServiceImpl implements IProductoReactiveService {

	@Autowired
	RestTemplate clienteRest;

	@Override
	@Transactional(readOnly = true)
	public Producto findById(String id) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id);

		Producto producto = clienteRest.getForObject("https://soyjose-productos.herokuapp.com/reactive/apirest/productos/ver/{id}",
				Producto.class, pathVariables);

		return producto;
	}

	@Override
	public List<Producto> findAll() {

		List<Producto> productos = clienteRest
				.getForObject("https://soyjose-productos.herokuapp.com/reactive/apirest/productos/listar-no-flux", List.class);

		return productos;
	}

	@Override
	public ResponseEntity<Resource> uploads(String filename) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("filename", filename);

		ResponseEntity<Resource> response = clienteRest.getForObject(
				"https://soyjose-productos.herokuapp.com/reactive/apirest/productos/uploads/{filename}", ResponseEntity.class,
				pathVariables);

		return response;
	}

	@Override
	public List<Producto> cargarProductosPorNombre(String term) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("term", term);

		List<Producto> productos = clienteRest.getForObject(
				"https://soyjose-productos.herokuapp.com/reactive/apirest/productos/nombre/{term}", List.class, pathVariables);

		return productos;
	}

}
