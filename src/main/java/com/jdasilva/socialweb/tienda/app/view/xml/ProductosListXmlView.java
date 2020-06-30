package com.jdasilva.socialweb.tienda.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.jdasilva.socialweb.commons.models.document.Producto;

@Component("home.xml")
public class ProductosListXmlView extends MarshallingView {

	@Autowired
	public ProductosListXmlView(Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("titulo");
		Producto[] productos = (Producto[]) model.get("productos");
		model.remove("productos");
		model.remove("mensajeFlash");

		ProductosWrapper productosList = new ProductosWrapper(productos);
		model.put("productosList", productosList);

		super.renderMergedOutputModel(model, request, response);
	}
}
