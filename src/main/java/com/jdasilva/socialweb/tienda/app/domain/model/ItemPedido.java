package com.jdasilva.socialweb.tienda.app.domain.model;

import java.io.Serializable;
//import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.jdasilva.socialweb.commons.models.document.Producto;

@Document(collection = "items_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	// private static DecimalFormat df = new DecimalFormat("0.00");

	@Id
	private String id;

	private Integer cantidad;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@NotNull
	private Producto producto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Double calcularImporte() {

		return Math.round((cantidad.doubleValue() * producto.getPrecio()) * 100.0) / 100.0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
