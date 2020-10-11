package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//import com.jdasilva.socialweb.tienda.app.domain.service.PedidoReactiveService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

	private static final Logger logger = LoggerFactory.getLogger(TiendaController.class);	
	
	@Autowired
	@Qualifier("productoRestServiceTienda")
	//@Qualifier("productoFeignServiceTienda")
	private IProductoService productoService;

//	@Autowired
//	PedidoReactiveService pedidoService;

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

			logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  productos.size " + productos.size());
			Producto[] productosArray = new Producto[productos.size()];
			productosArray = productoService.findAll().toArray(productosArray);			
			model.addAttribute("productos", productosArray);
		}
		
		return "home";
	}

}
