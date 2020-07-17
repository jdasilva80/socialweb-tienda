package com.jdasilva.socialweb.tienda.app.domain.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.jdasilva.socialweb.commons.models.document.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public Producto findById(@PathVariable String productoId);

	public ResponseEntity<Resource> uploads(@PathVariable String filename);

	public List<Producto> cargarProductosPorNombre(@PathVariable String term);

}