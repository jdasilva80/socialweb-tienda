package com.jdasilva.socialweb.tienda.app.view.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.jdasilva.socialweb.commons.models.document.Producto;

@XmlRootElement(name = "productos")
public class ProductosWrapper {

	@XmlElement(name = "producto")
	private Producto[] productos;

	public ProductosWrapper() {

	}
	
	public ProductosWrapper(Producto[] productos) {
		this.productos = productos;
	}

	public Producto[] getProductos() {
		return productos;
	}

}
