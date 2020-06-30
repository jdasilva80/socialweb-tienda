package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdasilva.socialweb.commons.models.document.Producto;
import com.jdasilva.socialweb.tienda.app.clientrest.ProductosClienteRestFeign;
import com.jdasilva.socialweb.tienda.app.clientrest.UsuariosClienteRestFeign;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	ProductosClienteRestFeign productosClient;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	UsuariosClienteRestFeign usuariosClient;

	@GetMapping({ "/", "" })
	public String home(Model model) {

		model.addAttribute("titulo", "tienda");
		return "home";
	}
	
	@GetMapping({ "/productos/lista"})
	public String productos(Model model) {

		model.addAttribute("titulo", "productos");
		
		List<Producto> productos =productosClient.findAll();
		
		if(productos.size()>0) {

			Producto[] productosArray = new Producto[productosClient.findAll().size()];
			productosArray = productosClient.findAll().toArray(productosArray);			
			model.addAttribute("productos", productosArray);
		}
		
		return "home";
	}

}
