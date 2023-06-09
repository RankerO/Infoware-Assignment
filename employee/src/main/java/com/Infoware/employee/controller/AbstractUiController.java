package com.Infoware.employee.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractUiController {


	@Value("${app.baseUrl}")
	private String baseUrl;


	protected ModelAndView mav(String page) {

		ModelAndView mav = new ModelAndView(page);
		return mav;
	}

}
