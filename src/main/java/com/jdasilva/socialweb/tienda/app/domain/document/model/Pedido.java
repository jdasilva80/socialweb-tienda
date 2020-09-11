package com.jdasilva.socialweb.tienda.app.domain.document.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;

@Document(collection = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String observacion;

	@DateTimeFormat(iso = ISO.DATE)
	@CreatedDate
	private Date fecha;

	@NotEmpty
	private List<ItemPedido> items;

	@NotNull
	private Usuario usuario;

	public Pedido() {
		items = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	public void addItem(ItemPedido itemPedido) {
		this.items.add(itemPedido);
	}

	public Double getTotal() {

		double total = 0;

		for (ItemPedido item : items) {
			total += item.calcularImporte();
		}
		return Math.round(total * 100.0) / 100.0;

	}

}
