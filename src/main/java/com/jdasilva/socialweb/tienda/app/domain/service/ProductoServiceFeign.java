package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jdasilva.socialweb.commons.models.productos.entity.Producto;
import com.jdasilva.socialweb.tienda.app.clientrest.ProductosClienteRestFeign;

@Service("productoFeignServiceTienda")
public class ProductoServiceFeign implements IProductoService {

	@Autowired(required = false)
	//@Autowired
	private ProductosClienteRestFeign productosRestFeign;

	@Override
	public List<Producto> findAll() {

		return productosRestFeign.findAll();
	}

	@Override
	public Producto findById(Long productoId) {

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
