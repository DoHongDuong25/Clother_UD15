package com.fpoly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="HomeControllerOfAdmin")
public class HomeController {
	@RequestMapping("/admin/home")
	public String home() {
		return "/admin/home/home";
	}
}
