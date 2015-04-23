package br.com.discipular.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	private final static String VIEW_INDEX = "index";
	
	@RequestMapping(value={"", "/"})
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(VIEW_INDEX);
		return view;
	}
	
}
