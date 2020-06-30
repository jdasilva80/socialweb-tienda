package com.jdasilva.socialweb.tienda.app.domain.model.listeners;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;

@Component
public class TiendaRepositoryListener extends AbstractMongoEventListener<Pedido> {

	@Override
	public void onBeforeSave(BeforeSaveEvent<Pedido> event) {

		event.getSource().setFecha(new Date());
	}
}
