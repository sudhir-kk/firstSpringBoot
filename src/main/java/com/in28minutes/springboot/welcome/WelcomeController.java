package com.in28minutes.springboot.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WelcomeController {

	@Autowired
	private MessageSource messageSource;
	@RequestMapping("/welcome/{name}")
	public String welcome(@PathVariable String name) {
		return "welcome "+name;
	}
	@RequestMapping("/welcome-internationalized")
	public String welcomeInternationalized() {
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
	}
}
