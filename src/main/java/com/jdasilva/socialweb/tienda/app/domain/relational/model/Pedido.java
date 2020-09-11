package com.jdasilva.socialweb.tienda.app.domain.relational.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String observacion;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatedDate
	private Date fecha;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pedido")
	@JsonManagedReference
	private List<ItemPedido> items;

	@Column(name = "usuario_id", nullable = false, updatable = false)
	private Long usuarioId;
	
	@Transient
	private Usuario usuario;

	public Pedido() {
		items = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Long getUsuario_id() {
		return usuarioId;
	}

	public void setUsuario_id(Long usuarioId, Usuario usuario) {
		
		this.usuario  = usuario;
		this.usuarioId = usuarioId;
	}

	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		if(usuario != null) {
			usuarioId  = usuario.getId();
		}
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
	
	@PrePersist
	private void prePersist() {
		fecha = new Date();
	}

}

