package com.jdasilva.socialweb.tienda.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.jdasilva.socialweb.commons.models.document.Producto;

@Component("homeReactive.xml")
public class ProductosReactiveListXmlView extends MarshallingView {

	@Autowired
	public ProductosReactiveListXmlView(Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("titulo");
		Producto[] productos = (Producto[]) model.get("productos");
		model.remove("productos");
		model.remove("mensajeFlash");

		ProductosReactiveWrapper productosList = new ProductosReactiveWrapper(productos);
		model.put("productosList", productosList);

		super.renderMergedOutputModel(model, request, response);
	}
}
