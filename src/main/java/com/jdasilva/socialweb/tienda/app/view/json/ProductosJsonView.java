package com.jdasilva.socialweb.tienda.app.view.json;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component("home.json")
public class ProductosJsonView extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		model.remove("titulo");
		
		return super.filterModel(model);
	}
}
