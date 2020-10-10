package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdasilva.socialweb.commons.models.productos.entity.Producto;
import com.jdasilva.socialweb.tienda.app.domain.relational.model.ItemPedido;
import com.jdasilva.socialweb.tienda.app.domain.relational.model.Pedido;
import com.jdasilva.socialweb.tienda.app.domain.service.IProductoService;
import com.jdasilva.socialweb.tienda.app.domain.service.IUsuarioService;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoReactiveService;
import com.jdasilva.socialweb.tienda.app.view.xml.ProductosWrapper;

@Controller
@RequestMapping("/tienda/productos")
@SessionAttributes("cesta")
public class ProductosController {

	private static final Logger logger = LoggerFactory.getLogger(ProductosController.class);
	
	@Autowired
	@Qualifier("productoRestServiceTienda")
	//@Qualifier("productoFeignServiceTienda")
	private IProductoService productoService;

	@Autowired
	PedidoReactiveService pedidoService;
	
	@Autowired
	@Qualifier("usuarioRestServiceTienda")
	//@Qualifier("usuarioFeingService")	
	private IUsuarioService usuarioService;

	@GetMapping(value = { "/", "" })
	public @ResponseBody List<Producto> cargarProductos() {

		return productoService.findAll();
	}

	@GetMapping(value = { "/xml" })
	public @ResponseBody ProductosWrapper cargarProductosXml() {

		List<Producto> productos =productoService.findAll();
		ProductosWrapper productosWrapper = null;
		if(productos.size()>0) {

			Producto[] productosArray = new Producto[productoService.findAll().size()];
			productosArray = productoService.findAll().toArray(productosArray);			
			productosWrapper = new ProductosWrapper(productosArray);			
		}
		return productosWrapper;
	}

	@GetMapping(value = "/nombre/{term}", produces = "application/json")
	public @ResponseBody List<Producto> cargarProductosNombre(@PathVariable String term) {

		logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% cargarProductosNombre");
		List<Producto> productos = productoService.cargarProductosPorNombre("%"+term+"%");
		logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% cargarProductosNombre size" + productos.size());

		return productos;
	}

	@GetMapping("/uploads/{filename:.+}")
	public ResponseEntity<Resource> verArchivo(@PathVariable String filename) {

		Resource recurso = productoService.uploads(filename).getBody();

		if (recurso != null) {

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(filename))
					.body(recurso);
		} else {

			return ResponseEntity.noContent().build();
		}

	}

	@GetMapping("/{productoId}/cantidad/{cantidad}")
	public void incrementarCantidadItem(@SessionAttribute("cesta") Pedido cesta, @PathVariable Long productoId,
			@PathVariable Integer cantidad) {

		if (cesta != null) {

			ItemPedido itemPedido = cesta.getItems().stream()
					.filter((item) -> item.getProductoId().equals(productoId)).findFirst().orElse(null);

			if (itemPedido == null) {

				Producto producto = productoService.findById(productoId);
				itemPedido = new ItemPedido();
				itemPedido.setProductoId(productoId, producto);
				cesta.addItem(itemPedido);
			}
			itemPedido.setCantidad(cantidad);
		}
	}
}
