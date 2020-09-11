package com.jdasilva.socialweb.tienda.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdasilva.socialweb.commons.models.document.Producto;
import com.jdasilva.socialweb.commons.models.usuarios.entity.Mensaje;
import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.tienda.app.domain.service.IUsuarioService;
import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;
import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;
import com.jdasilva.socialweb.tienda.app.domain.service.IProductoReactiveService;
import com.jdasilva.socialweb.tienda.app.domain.service.IUploadService;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoReactiveService;

@Controller
@RequestMapping("/reactive/tienda/pedidos")
@SessionAttributes("cesta")
public class PedidosReactiveController {

	@Autowired
	PedidoReactiveService pedidoService;

	@Autowired
//	@Qualifier("productoReactiveRestServiceTienda")
	@Qualifier("productoReactiveFeignServiceTienda")
	private IProductoReactiveService productoService;

//	@Autowired
	@Autowired
//	@Qualifier("usuarioRestServiceTienda")
	@Qualifier("usuarioFeingService")	
	private IUsuarioService usuarioService;

	@Autowired
	IUploadService uploadService;

	@Value("${path.zuul}")
	private String pathZuul;

	@Autowired
	private MessageSource messages;

	@GetMapping({ "/", "" })
	public String pedidos(Model model) {

		model.addAttribute("titulo", "pedidos");
		model.addAttribute("pedidos", pedidoService.findAll());

		return "pedidosListaReactive";
	}

	@GetMapping({ "/usuario/username/{username}" })
	public String pedidosUsuario(@PathVariable String username, Model model) {

		Usuario usuario = usuarioService.findByUsername(username);

		model.addAttribute("titulo", "pedidos");
		model.addAttribute("pedidos", pedidoService.findByUsuario(usuario));

		return "pedidosListaReactive";
	}

	@GetMapping({ "/{id}" })
	public String getPedido(@PathVariable @NotNull String id, Model model) {

		Pedido pedido = pedidoService.findById(id).orElse(null);

		if (pedido != null) {
			model.addAttribute("pedido", pedido);
			model.addAttribute("titulo", "pedido " + pedido.getId());
		}

		return "itemsPedidoReactive";
	}

	@GetMapping({ "/eliminar/{id}" })
	public String eliminarPedido(@PathVariable @NotNull String id, Model model) {

		Pedido pedido = pedidoService.findById(id).orElse(null);

		if (pedido != null) {

			pedidoService.delete(pedido);
			model.addAttribute("pedidos", pedidoService.findAll());

			Mensaje mensaje = new Mensaje();
			List<String> danger = new ArrayList<>();
			danger.add("se ha eliminado el pedido ".concat(pedido.getId()));
			mensaje.setDanger(danger);
			model.addAttribute("mensaje", mensaje);
		}

		return "pedidosListaReactive";
	}

	@GetMapping({ "/usuario/username/{username}/pedido/{id}" })
	public String usuarioProductos(@PathVariable String username, @PathVariable String id, Model model) {

		Usuario usuario = usuarioService.findByUsername(username);

		if (usuario != null) {

			Pedido pedido = pedidoService.findByUsuario(usuario).stream().filter((p) -> p.getId().equals(id))
					.findFirst().orElse(null);

			if (pedido != null) {
				model.addAttribute("pedido", pedido);

			}
		}
		return "itemsPedidoReactive";
	}

	@GetMapping({ "/comprar/{productoId}" })
	public String usuarioProductos(@PathVariable String productoId, Model model,
			@SessionAttribute(name = "cesta", required = false) Pedido cesta, Locale locale) {

		Producto producto = productoService.findById(productoId);

		if (cesta == null) {

			Usuario usuario = usuarioService.findByUsername("jdasilva1980");
			cesta = new Pedido();
			cesta.setUsuario(usuario);
			model.addAttribute("cesta", cesta);
		}

		ItemPedido itemPedido = cesta.getItems().stream()
				.filter((item) -> item.getProducto().getId().equals(productoId)).findFirst().orElse(new ItemPedido());

		if (itemPedido.getProducto() == null) {

			itemPedido.setProducto(producto);
			itemPedido.setCantidad(1);
			cesta.addItem(itemPedido);

		} else {
			itemPedido.setCantidad(itemPedido.getCantidad() != null ? itemPedido.getCantidad() + 1 : 1);
		}

		model.addAttribute("titulo", messages.getMessage("text.cesta.titulo", null, "Cesta", locale));

		Mensaje mensaje = new Mensaje();
		List<String> info = new ArrayList<>();
		info.add("se ha añadido el producto ".concat(producto.getNombre()));
		mensaje.setInfo(info);
		model.addAttribute("mensaje", mensaje);

		return "cestaReactive";
	}

	@GetMapping({ "/vaciarcesta" })
	public String vaciarCesta(@SessionAttribute(name = "cesta", required = false) Pedido pedido, Model model,
			SessionStatus sessionStatus, Locale locale) {

		if (pedido != null) {

			pedido.getItems().removeIf((item) -> item.getProducto() != null);

			sessionStatus.setComplete();
		}

		model.addAttribute("titulo", messages.getMessage("text.cesta.titulo", null, "Cesta", locale));

		return "cestaReactive";

	}

	@GetMapping({ "/{id}/eliminar/item/{idItem}" })
	public String eliminarItemPedido(@PathVariable String id, @PathVariable String idItem, Model model) {

		Pedido pedido = pedidoService.findById(id).orElse(null);

		if (pedido != null) {

			ItemPedido item = pedido.getItems().stream().filter((i) -> i.getId().equals(idItem)).findFirst()
					.orElse(null);

			if (item != null) {

				pedido.getItems().remove(item);
				pedidoService.insert(pedido);
				model.addAttribute("titulo", "item eliminado ".concat(item.getId()));

			}

			model.addAttribute("pedido", pedido);
		}

		return "itemsPedidoReactive";

	}

	@GetMapping({ "/vaciarcesta/producto/{productoId}" })
	public String vaciarCestaProducto(@PathVariable String productoId, @SessionAttribute(name = "cesta") Pedido pedido,
			Model model, SessionStatus sessionStatus, Locale locale) {

		Producto producto = productoService.findById(productoId);

		if (producto != null) {

			pedido.getItems().removeIf((item) -> item.getProducto().getId().equals(productoId));

			Mensaje mensaje = new Mensaje();
			List<String> danger = new ArrayList<>();
			danger.add("se ha eliminado el producto ".concat(producto.getNombre()));
			mensaje.setDanger(danger);
			model.addAttribute("mensaje", mensaje);

			if (pedido.getItems().size() == 0) {

				sessionStatus.setComplete();
			}
		}
		model.addAttribute("titulo", messages.getMessage("text.cesta.titulo", null, "Cesta", locale));

		return "cestaReactive";

	}

	@GetMapping({ "/cesta/usuario" })
	public String cesta(@SessionAttribute(name = "cesta", required = false) Pedido cesta, Model model, Locale locale) {

		if (cesta == null) {

			cesta = new Pedido();
			model.addAttribute("cesta", cesta);
		}

		Usuario usuario = usuarioService.findByUsername("jdasilva1980");

		if (usuario == null) {

			Mensaje mensaje = new Mensaje();
			List<String> danger = new ArrayList<>();
			danger.add("No existe el usuario ");
			mensaje.setDanger(danger);
			model.addAttribute("mensaje", mensaje);
		}

		cesta.setUsuario(usuario);
		model.addAttribute("titulo", messages.getMessage("text.cesta.titulo", null, "Cesta", locale));

		return "cestaReactive";
	}

	@PostMapping("/cesta/form")
	public String guardarCesta(Pedido cesta,
			@RequestParam(name = "producto_id[]", required = false) String[] productosId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidades, RedirectAttributes flash,
			SessionStatus status) {

		Usuario usuario = usuarioService.findByUsername("jdasilva1980");

		if (usuario != null) {

			cesta.setUsuario(usuario);
		}

		Mensaje mensaje = new Mensaje();
		flash.addFlashAttribute("mensajeFlash", mensaje);

		if (productosId != null) {

			Arrays.asList(productosId).forEach((id) -> {

				ItemPedido itemPedido = cesta.getItems().stream()
						.filter((item) -> item.getProducto().getId().equals(id)).findFirst().orElse(null);

				if (itemPedido == null) {

					ItemPedido item = new ItemPedido();
					Producto producto = productoService.findById(id);
					item.setProducto(producto);
					int posicion = Arrays.asList(productosId).indexOf(id);
					item.setCantidad(cantidades[posicion]);
					cesta.addItem(item);
				}
			});

			pedidoService.insert(cesta);

			List<String> info = new ArrayList<>();
			info.add("se ha enviado el pedido con id " + cesta.getId());
			mensaje.setInfo(info);

		} else {

			List<String> danger = new ArrayList<>();
			danger.add("no se ha seleccionado ningun producto");
			mensaje.setDanger(danger);
		}

		status.setComplete();

		return "redirect:".concat(pathZuul).concat("/tienda");
	}

}
