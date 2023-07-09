package com.fpoly.controller.customer.KhachHang;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dto.HoaDonChiTietDTO;
import com.fpoly.dto.HoaDonDTO;
import com.fpoly.service.HoaDonChiTietService;
import com.fpoly.service.HoaDonService;

@Controller
public class HoaDonKhachHangController {
	
	@Autowired
	private HoaDonService hoaDonService ;
	
	@Autowired
	private HoaDonChiTietService hoaDonChiTietService ;
	
	
		@GetMapping("/customer/don-hang")
		public String getDanhSachHoaDon(@RequestParam(name="page",defaultValue="1",required=true) Integer page ,Model model) {
			Pageable pageable = PageRequest.of(page-1,5, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "id"));
			//Truyền mã khách hàng bằng spring security vô đây
			HoaDonDTO hoaDonDTO = hoaDonService.findAllByLoaiHoaDonAndMaKhachHang(0,(long) 1,pageable);
			model.addAttribute("hoaDonDTO",hoaDonDTO);
			return "/customer/HoaDon/danh-sach-hoa-don";
		}
		
		@GetMapping("/customer/don-hang/chi-tiet-don-hang")
		public String chiTietDonHang(@RequestParam(name="maDonHang",required=false) String maDonHang ,
				@RequestParam(name="page",required=false) Integer page ,Model model) {
			Pageable pageable = PageRequest.of(page-1,2, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "id"));
			HoaDonChiTietDTO hoaDonChiTietDTO = hoaDonChiTietService.findByHoaDonId(maDonHang,pageable);
			model.addAttribute("hoaDonChiTietDTO",hoaDonChiTietDTO);
			model.addAttribute("maDonHang",maDonHang);
			getDanhSachHoaDon(page, model);
			return "/customer/HoaDon/danh-sach-hoa-don";
		}
		@GetMapping("/customer/don-hang/huy-don-hang")
		public String huyDonHang(@RequestParam(name="maDonHang",required=false) String maDonHang ,
				@RequestParam(name="page",required=false) Integer page ,Model model , HttpServletRequest request) {
			
			if(maDonHang != null) {
				hoaDonService.capNhatTrangThaiHuyDon(maDonHang);
				getDanhSachHoaDon(page, model);
				chiTietDonHang(maDonHang, page, model);
			}
			return "/customer/HoaDon/danh-sach-hoa-don";
		}
		
	
}
//package com.fpoly.controller.customer.KhachHang;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HoaDonKhachHangController {
//		@GetMapping("/customer/hoa-don")
//		public String getDanhSachHoaDon() {
//			return "/customer/HoaDon/danh-sach-hoa-don";
//		}
//
//}
