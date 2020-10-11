package com.jdasilva.socialweb.tienda.app.view.xml;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.jdasilva.socialweb.commons.models.productos.entity.Producto;

@Component("home.xml")
public class ProductosListXmlView extends MarshallingView {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductosListXmlView.class);

	@Autowired
	public ProductosListXmlView(Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("titulo");
		//Producto[] productos = (Producto[]) model.get("productos");
		List<Producto> productos = (List<Producto>) model.get("productos");	
		logger.info("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨- productos.size " + productos.size());
		model.remove("productos");
		model.remove("mensajeFlash");
		
		ProductosWrapper productosList = null; 
		
		if(productos.size()>0) {
			
			Producto[] productosArray = new Producto[productos.size()];
			
			for(int i=0; i<productos.size(); i++) {	
				productosArray[i] = productos.get(i);
			}
			
//			for(int i=0; i<productos.size(); i++) {				
//				
//				for (Map.Entry<?, ?> entry : productos.get(i).entrySet()) {
//					
//					logger.info(" *********************** class--> i:"+ i + " " + entry.getClass());
//				    
//					Object key = entry.getKey();
//					logger.info(" --------------------- key, " + key.toString());
//				    Object value = entry.getValue();
//				    logger.info(" --------------------- value, " +value.toString());
//				    
//				    if(value instanceof Producto) {
//
//					    logger.info(" --------------------- value, " + ((Producto)value).getNombre());
//						productosArray[i] = (Producto)value;
//						
//				    }else if(key instanceof Producto) {
//				    	logger.info(" :::::::::::::::::::::: key, " + ((Producto)value).getNombre());
//						productosArray[i] = (Producto)key;
//				    }
//				}
//			}
			productosList = new ProductosWrapper(productosArray);
			
		}else {			
			productosList = new ProductosWrapper();
		}
		model.put("productosList", productosList);

		super.renderMergedOutputModel(model, request, response);
	}
}
