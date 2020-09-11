package com.jdasilva.socialweb.tienda.app.domain.relational.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdasilva.socialweb.commons.models.productos.entity.Producto;

@Entity
@Table(name = "items_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@NotNull
	private Long productoId;
	
	@Transient
	private Producto producto;
	
	@JoinColumn(nullable = false, updatable = false, foreignKey = @ForeignKey(name = "Fk_item_pedido_id"))
	@ManyToOne
	@JsonIgnore
	private Pedido pedido;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId, Producto producto) {
		
		this.producto = producto;
		this.productoId = productoId;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Double calcularImporte() {		

		if( this.producto == null ) {
				
			return Math.round((cantidad.doubleValue() * producto.getPrecio()) * 100.0) / 100.0;			
		}
		return Double.MIN_VALUE;		
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
