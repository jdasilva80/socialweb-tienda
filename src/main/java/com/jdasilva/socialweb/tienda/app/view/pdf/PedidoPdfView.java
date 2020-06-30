package com.jdasilva.socialweb.tienda.app.view.pdf;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("itemsPedido") //tiene que tener el nombre de la vista que devuelve el handler
public class PedidoPdfView extends AbstractPdfView {

	@Autowired
	private MessageSource messages;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Locale locale = localeResolver.resolveLocale(request);

		Pedido pedido = (Pedido) model.get("pedido");

		// MessageSourceAccessor mensajes = getMessageSourceAccessor();//se evita hacer
		// lo del locale, se hereda a través de la superclase AbstractPdfView

		PdfPTable table0 = new PdfPTable(1);
		Phrase phrase1 = new Phrase(messages.getMessage("text.pdf.pedido.titulo", null, "Pedido pdf", locale));
		// Phrase phrase2 = new Phrase(mensajes.getMessage("text.pdf.pedido.titulo"));
		table0.addCell(phrase1);
		table0.setSpacingAfter(20);

		PdfPTable table1 = new PdfPTable(1);

		table1.addCell("Usuario.");
		table1.setSpacingAfter(20);
		table1.addCell(pedido.getUsuario().getNombre().concat(" ").concat(pedido.getUsuario().getApellidos()));

		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		table2.addCell("Datos del pedido.");
		table2.addCell("Id: ".concat(pedido.getId()));
		table2.addCell("Fecha: ".concat(pedido.getFecha().toString()));
		table2.addCell("Observación: ".concat(pedido.getObservacion() == null ? "" : pedido.getObservacion()));

		PdfPTable table3 = new PdfPTable(1);
		table3.setSpacingAfter(20);
		table3.addCell("Detalle de items.");
		pedido.getItems().forEach(

				(item) -> {
					table3.addCell("id: ".concat(item.getProducto().getNombre()).concat(" ,cantidad: ")
							.concat(item.getCantidad().toString()).concat(" ,precio: ")
							.concat(item.getProducto().getPrecio().toString()));
				});
		table3.addCell("Importe total: ".concat(pedido.getTotal().toString()));

		document.add(table0);
		document.add(table1);
		document.add(table2);
		document.add(table3);

	}

}
