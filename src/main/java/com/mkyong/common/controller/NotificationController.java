package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@SessionAttributes
public class NotificationController {

	@RequestMapping(value = "/setemail.html", method = RequestMethod.GET)

	public void simpleForm(Model model) {

	model.addAttribute(new NotificationChanger());

	}
}