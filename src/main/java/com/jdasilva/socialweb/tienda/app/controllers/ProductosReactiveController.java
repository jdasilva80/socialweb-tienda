package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.List;

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

import com.jdasilva.socialweb.commons.models.document.Producto;
import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;
import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;
import com.jdasilva.socialweb.tienda.app.domain.service.IProductoReactiveService;
import com.jdasilva.socialweb.tienda.app.domain.service.IUsuarioService;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoReactiveService;
import com.jdasilva.socialweb.tienda.app.view.xml.ProductosReactiveWrapper;

@Controller
@RequestMapping("/reactive/tienda/productos")
@SessionAttributes("cesta")
public class ProductosReactiveController {

	@Autowired(required = false)
	@Qualifier("productoRestServiceTienda")
	//@Qualifier("productoReactiveFeignServiceTienda")
	private IProductoReactiveService productoService;

	@Autowired(required = false)
	PedidoReactiveService pedidoService;

//	@Autowired
//	UsuariosClienteRestFeign usuariosClient;
	@Autowired
	@Qualifier("usuarioFeingService")
	private IUsuarioService usuarioService;

	@GetMapping(value = { "/", "" })
	public @ResponseBody List<Producto> cargarProductos() {

		return productoService.findAll();
	}

	@GetMapping(value = { "/xml" })
	public @ResponseBody ProductosReactiveWrapper cargarProductosXml() {

		List<Producto> productos =productoService.findAll();
		ProductosReactiveWrapper productosWrapper = null;
		if(productos.size()>0) {

			Producto[] productosArray = new Producto[productoService.findAll().size()];
			productosArray = productoService.findAll().toArray(productosArray);			
			productosWrapper = new ProductosReactiveWrapper(productosArray);			
		}
		return productosWrapper;
	}

	@GetMapping(value = "/nombre/{term}", produces = "application/json")
	public @ResponseBody List<Producto> cargarProductosNombre(@PathVariable String term) {

		List<Producto> productos = productoService.cargarProductosPorNombre(term);

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
	public void incrementarCantidadItem(@SessionAttribute("cesta") Pedido cesta, @PathVariable String productoId,
			@PathVariable Integer cantidad) {

		if (cesta != null) {

			ItemPedido itemPedido = cesta.getItems().stream()
					.filter((item) -> item.getProducto().getId().equals(productoId)).findFirst().orElse(null);

			if (itemPedido == null) {

				Producto producto = productoService.findById(productoId);
				itemPedido = new ItemPedido();
				itemPedido.setProducto(producto);
				cesta.addItem(itemPedido);
			}
			itemPedido.setCantidad(cantidad);
		}
	}
}
