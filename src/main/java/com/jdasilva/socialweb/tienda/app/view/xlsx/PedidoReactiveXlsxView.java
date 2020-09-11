package com.jdasilva.socialweb.tienda.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.jdasilva.socialweb.tienda.app.domain.document.model.Pedido;

@Component("itemsPedidoReactive.xlsx")
public class PedidoReactiveXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Pedido pedido = (Pedido) model.get("pedido");

		MessageSourceAccessor messages = getMessageSourceAccessor();// se evita hacer lo del locale, se hereda a través
																	// de la superclase AbstractXlsxView
		
		Sheet sheet = workbook.createSheet();
		
		sheet.createRow(0).createCell(0).setCellValue(messages.getMessage("text.xlsx.pedido.titulo"));
		sheet.createRow(2).createCell(0).setCellValue("Id: ".concat(pedido.getId()));
		sheet.createRow(4).createCell(0).setCellValue("Fecha: ".concat(pedido.getFecha().toString()));
		sheet.createRow(6).createCell(0).setCellValue("Observación: ".concat(pedido.getObservacion() == null ? "" : pedido.getObservacion()));
		sheet.createRow(8).createCell(0).setCellValue("Importe total: ".concat(pedido.getTotal().toString()));
		sheet.createRow(9).createCell(0).setCellValue("");
		
		pedido.getItems().forEach(
				
				
				(item) -> {
					sheet.createRow(sheet.getLastRowNum()+1).createCell(0).setCellValue("id: ".concat(item.getProducto().getNombre()).concat(" ,cantidad: ")
							.concat(item.getCantidad().toString()).concat(" ,precio: ")
							.concat(item.getProducto().getPrecio().toString()));
				});

	}

}
