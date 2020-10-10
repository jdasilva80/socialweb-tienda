package com.jdasilva.socialweb.tienda.app;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.mongodb.config.EnableMongoAuditing;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.jdasilva.socialweb.commons.interceptors.FlashAttributesInterceptor;
//import com.jdasilva.socialweb.commons.models.document.Producto;
//import com.jdasilva.socialweb.tienda.app.domain.document.model.ItemPedido;
import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;
//import com.jdasilva.socialweb.tienda.app.domain.service.IProductoReactiveService;
//import com.jdasilva.socialweb.tienda.app.domain.service.IProductoService;
//import com.jdasilva.socialweb.tienda.app.domain.service.IUsuarioService;

//@EnableMongoRepositories(basePackageClasses = {PedidoDao.class})
//@EnableMongoAuditing // es necesario para que funcione @createdData
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackageClasses = { FlashAttributesInterceptor.class, SocialwebTiendaApplication.class })
@EntityScan(basePackageClasses = { Pedido.class,
		com.jdasilva.socialweb.tienda.app.domain.relational.model.Pedido.class })
public class SocialwebTiendaApplication implements CommandLineRunner {

//	private ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;

//	@Autowired
//	private PedidoService pedidoService;
//
//	@Autowired
//	private ItemPedidoService itemPedidoService;

//	@Autowired
//	@Qualifier("productoRestServiceTienda")
//	@Qualifier("productoFeignServiceTienda")
//	private IProductoService productoService;
//
//	@Autowired
//	@Qualifier("usuarioRestServiceTienda")
//	@Qualifier("usuarioFeingService")
//	private IUsuarioService usuarioService;

//	@Autowired
//	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SocialwebTiendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		mongoTemplate.dropCollection(ItemPedido.class);
//		mongoTemplate.dropCollection(Pedido.class);
//		mongoTemplate.createCollection(Pedido.class);
//		mongoTemplate.createCollection(ItemPedido.class);

//		List<LinkedHashMap<String, ?>> usuarios = (List<LinkedHashMap<String, ?>>) usuarioService.findAll()
//				.get("_embedded").get("usuarios");

//		List<com.jdasilva.socialweb.commons.models.productos.entity.Producto> productos = productoService.findAll();
//
//		usuarios.forEach(
//
//				(element) -> {
//					Pedido pedido = new Pedido();
//					pedido.setUsuario(
//							usuarioService.findByUsername((String) ((LinkedHashMap<?, ?>) element).get("username")));
//
//					productos.forEach((p) -> {
//
//						ItemPedido item = new ItemPedido();
//						item.setCantidad(2);
//						item.setProducto(p);
//						itemPedidoService.insert(item);
//						pedido.addItem(item);
//
//					});
//					pedidoService.insert(pedido);
//				});
	}

	@Bean
	public LocaleResolver localeResolver() {

		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es", "ES"));

		return localeResolver;

	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {

		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean // serializador a XML (Marshalling)
	public Jaxb2Marshaller Jaxb2Marshaller() {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller
				.setClassesToBeBound(new Class[] { com.jdasilva.socialweb.tienda.app.view.xml.ProductosWrapper.class });
		// ProductosList -> clase envoltorio, ya que no se puede hacer marshelling de
		// Collections(list,set,..) directamente con jaxb (con json no es necesario, es
		// directo)
		return jaxb2Marshaller;
	}

	@Bean
	// @LoadBalanced para usar ribbon con restamplate
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response,
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

//	
//	@Bean
//    Encoder feignFormEncoder() {
//        return new SpringFormEncoder(new SpringEncoder(messageConverters));
//    }
//
//    @Bean
//    Decoder feignFormDecoder() {
//        return new SpringDecoder(messageConverters);
//    }
}
