package com.fpoly.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="HomeControllerOfCustomer")
public class HomeController {
	@RequestMapping("/customer/home")
	public String home() {
		return "/customer/home/home";
	}
}
