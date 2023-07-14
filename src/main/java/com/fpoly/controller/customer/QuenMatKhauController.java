package com.fpoly.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.KhachHang;
import com.fpoly.service.KhachHangService;

@Controller
public class QuenMatKhauController {
	@Autowired
	private KhachHangService khachHangService ;
	
	@Autowired
	private HttpSession session ;
	
	
	@RequestMapping("/security/forgot-password")
	public String form( Model model) {
		model.addAttribute("khachHangDTO",new KhachHangDTO());
		return "/customer/auth/forgot-password" ;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/security/forgot-password/enter-code")
	public String enterCode(
							@ModelAttribute("khachHangDTO") KhachHangDTO khachHangDTO ,
							Model model) {
		String code = (String) session.getValue("code");
		String email = (String) session.getValue("email");
		if(code.equals(khachHangDTO.getCodeSend())) {
				khachHangService.updatePassword(email,khachHangDTO);
				model.addAttribute("message","Đổi mật khẩu thành công !");
				return "/customer/auth/forgot-password" ;
			
		}else if(!code.equals(khachHangDTO.getCodeSend())) {
			model.addAttribute("messageError","Không đúng mã !");
			return "/customer/auth/forgot-password" ;
		}
		return "/customer/auth/forgot-password" ;
	}
	
	@SuppressWarnings({"deprecation" })
	@RequestMapping("/security/forgot-password/sendCode")
	public String sendCode( Model model ) {
		String email = (String) session.getValue("email");
		String code = khachHangService.sendCode(email);
		session.removeAttribute("code");
		session.putValue("code",code);
		return "/customer/auth/forgot-password" ;
	}
	
}
