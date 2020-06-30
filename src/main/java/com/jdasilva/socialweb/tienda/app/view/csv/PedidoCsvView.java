package com.jdasilva.socialweb.tienda.app.view.csv;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.jdasilva.socialweb.tienda.app.domain.model.Pedido;
import com.jdasilva.socialweb.tienda.app.domain.service.PedidoService;

@Component("pedidosLista") // tiene que tener el nombre de la vista que devuelve el handler
public class PedidoCsvView extends AbstractView {

//	@Autowired
//	private MessageSource messages;

	@Autowired
	PedidoService pedidoService;

	public PedidoCsvView() {

		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"pedidos.csv\"");
		response.setContentType(getContentType());

		List<Pedido> pedidos = (List<Pedido>) model.get("pedidos");

		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id", "fecha", "observacion" };
		beanWriter.writeHeader(header);

		for (Pedido pedido : pedidos) {
			beanWriter.write(pedido, header);
		}

		beanWriter.close();

	}

}
