package com.jdasilva.socialweb.tienda.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tienda")
public class LocaleController {

	@Value("${path.zuul}")
	private String pathZuul;
	
	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		
		String ultimaUrl = request.getHeader("referer");
		
		return "redirect:".concat(ultimaUrl);
	}
	
}
