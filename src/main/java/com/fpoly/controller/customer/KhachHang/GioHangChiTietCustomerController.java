package com.fpoly.controller.customer.KhachHang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dto.GioHangDTO;
import com.fpoly.service.GioHangChiTietService;
import com.fpoly.service.GioHangService;

@Controller
public class GioHangChiTietCustomerController {
		
		@Autowired
		private GioHangService gioHangService ;
		
		@Autowired
		private GioHangChiTietService gioHangChiTietService ;
	
		@GetMapping("/customer/gio-hang-chi-tiet")
		public String layGioHangChiTiet( Model model) {
			 GioHangDTO gioHangDTO = gioHangService.findByKhachHangId((long) 1);
			 model.addAttribute("gioHangDTO",gioHangDTO);
			return "/customer/khach-hang/gio-hang-chi-tiet";
			
		}
		
		@PostMapping("/customer/gio-hang-chi-tiet")
		public String capNhatGioHang(@RequestParam("ids") Long[] ids
												,@RequestParam("soLuongs") Integer[] soLuongs , Model model) {
			layGioHangChiTiet(model);
			for (Integer soLuong : soLuongs) {
				if(soLuong == null) {
					model.addAttribute("messageError","Số lượng trống !");
					return "/customer/khach-hang/gio-hang-chi-tiet";
				}else if(soLuong <= 0) {
					model.addAttribute("messageError","Số phải lớn hơn 0");
					return "/customer/khach-hang/gio-hang-chi-tiet";
				}
			}
			gioHangChiTietService.capNhatSoLuongGioHangChiTiet(ids,soLuongs);
			//Mốt là thay bằng spring security
			gioHangService.capNhatTongTien((long) 1);
			return "redirect:/customer/gio-hang-chi-tiet";
			
		}
//		@GetMapping("/customer/gio-hang-chi-tiet/xoa")
//		public String xoaGioHang(@RequestParam("ids") Long[] ids
//												,@RequestParam("soLuongs") Integer[] soLuongs , Model model) {
//			layGioHangChiTiet(model);
//			for (Integer soLuong : soLuongs) {
//				if(soLuong == null) {
//					model.addAttribute("messageError","Số lượng trống !");
//					return "/customer/khach-hang/gio-hang-chi-tiet";
//				}else if(soLuong <= 0) {
//					model.addAttribute("messageError","Số phải lớn hơn 0");
//					return "/customer/khach-hang/gio-hang-chi-tiet";
//				}
//			}
//			gioHangChiTietService.capNhatSoLuongGioHangChiTiet(ids,soLuongs);
//			return "redirect:/customer/gio-hang-chi-tiet";
//		}
}
