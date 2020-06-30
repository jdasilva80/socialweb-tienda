package com.jdasilva.socialweb.tienda.app.clientrest;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jdasilva.socialweb.commons.models.document.Producto;

@FeignClient(name = "socialweb-flux-productos")
public interface ProductosClienteRestFeign {

	@GetMapping("/apirest/productos/listar-no-flux")
	public List<Producto> findAll();

	@GetMapping("/apirest/productos/findById-no-flux/{productoId}")
	public Producto findById(@PathVariable String productoId);
	
	@GetMapping("/apirest/productos/uploads/{filename}")
	public ResponseEntity<Resource> uploads(@PathVariable String filename);
	
	@GetMapping("/apirest/productos/nombre/{term}")
	public List<Producto> cargarProductosPorNombre(@PathVariable String term);
	
}
