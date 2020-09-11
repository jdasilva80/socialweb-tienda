package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jdasilva.socialweb.commons.models.document.Producto;
import com.jdasilva.socialweb.tienda.app.clientrest.ProductosReactiveClienteRestFeign;

@Service("productoReactiveFeignServiceTienda")
public class ProductoReactiveServiceFeign implements IProductoReactiveService {

	@Autowired(required = false)
	private ProductosReactiveClienteRestFeign productosRestFeign;

	@Override
	public List<Producto> findAll() {

		return productosRestFeign.findAll();
	}

	@Override
	public Producto findById(String productoId) {

		return productosRestFeign.findById(productoId);
	}

	@Override
	public ResponseEntity<Resource> uploads(String filename) {

		return productosRestFeign.uploads(filename);
	}

	@Override
	public List<Producto> cargarProductosPorNombre(String term) {

		return productosRestFeign.cargarProductosPorNombre(term);
	}

}
