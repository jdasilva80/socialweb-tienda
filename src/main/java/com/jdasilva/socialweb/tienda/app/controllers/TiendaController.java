package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdasilva.socialweb.commons.models.productos.entity.Producto;
//import com.jdasilva.socialweb.tienda.app.clientrest.ProductosClienteRestFeign;
import com.jdasilva.socialweb.tienda.app.domain.service.IProductoService;
//import com.jdasilva.socialweb.tienda.app.clientrest.UsuariosClienteRestFeign;
import com.jdasilva.socialweb.tienda.app.domain.service.IUsuarioService;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoReactiveService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	@Qualifier("productoRestServiceTienda")
	//@Qualifier("productoFeignServiceTienda")
	private IProductoService productoService;

	@Autowired
	PedidoReactiveService pedidoService;

//	@Autowired
//	UsuariosClienteRestFeign usuariosClient;
	@Autowired
	@Qualifier("usuarioRestServiceTienda")
	private IUsuarioService usuarioService;

	@GetMapping({ "/", "" })
	public String home(Model model) {

		model.addAttribute("titulo", "tienda");
		return "home";
	}
	
	@GetMapping({ "/productos/lista"})
	public String productos(Model model) {

		model.addAttribute("titulo", "productos");
		
		List<Producto> productos =productoService.findAll();
		
		if(productos.size()>0) {

			Producto[] productosArray = new Producto[productoService.findAll().size()];
			productosArray = productoService.findAll().toArray(productosArray);			
			model.addAttribute("productos", productosArray);
		}
		
		return "home";
	}

}
