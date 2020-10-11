package com.jdasilva.socialweb.tienda.app.view.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.jdasilva.socialweb.commons.models.productos.entity.Categoria;
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
		// Producto[] productos = (Producto[]) model.get("productos");
		List<LinkedHashMap<?, ?>> productos = (List<LinkedHashMap<?, ?>>) model.get("productos");
		model.remove("productos");
		model.remove("mensajeFlash");

		ProductosWrapper productosList = null;

		if (productos.size() > 0) {

			Producto[] productosArray = new Producto[productos.size()];

			for (int i = 0; i < productos.size(); i++) {

				Producto prod = new Producto();

				for (Map.Entry<?, ?> entry : productos.get(i).entrySet()) {

					logger.info(" *********************** class--> i:" + i + " " + entry.getClass());

					Object key = entry.getKey();
					logger.info(" --------------------- key, " + key.toString());
					Object value = entry.getValue();
					logger.info(" --------------------- value, " + value.toString());

					if ("precio".equals(key)) {
						prod.setPrecio((Double) value);

					} else if ("foto".equals(key)) {
						prod.setFoto((String) value);

					} else if ("nombre".equals(key)) {
						prod.setNombre((String) value);

					} else if ("createAt".equals(key)) {
						
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				        Date fechaDate = null;
				        try {
				            fechaDate = formato.parse((String)value);
				            prod.setCreateAt(fechaDate);
				        } 
				        catch (ParseException ex) 
				        {
				            logger.error("--error " + ex.getMessage());
				        }
						
					} else if ("id".equals(key)) {
						prod.setId(((Integer) value).longValue());
					
					} else if ("categoria".equals(key)) {
						
						Categoria cat = new Categoria();						
						cat.setId(((Integer)((Map)value).get("id")).longValue());
						cat.setNombre((String)((Map)value).get("nombre"));						
						prod.setCategoria(cat);
					}
				}
				productosArray[i] = prod;
			}
			productosList = new ProductosWrapper(productosArray);

		} else

		{
			productosList = new ProductosWrapper();
		}
		model.put("productosList", productosList);

		super.renderMergedOutputModel(model, request, response);
	}
}
