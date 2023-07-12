package com.fpoly.controller.customer.KhachHang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dto.GioHangDTO;
import com.fpoly.dto.KhachHangDTO;
import com.fpoly.dto.KhuyenMaiDTO;
import com.fpoly.security.NguoiDungDetails;
import com.fpoly.service.GioHangChiTietService;
import com.fpoly.service.GioHangService;
import com.fpoly.service.KhachHangService;
import com.fpoly.service.KhuyenMaiService;

@Controller
public class GioHangChiTietCustomerController {
		
		@Autowired
		private GioHangService gioHangService ;
		
		@Autowired
		private GioHangChiTietService gioHangChiTietService ;
		
//		@Autowired
//		private NguoiDungService nguoiDungService ;
		
		@Autowired
		private KhuyenMaiService khuyenMaiService ;
		
		@Autowired
		private KhachHangService khachHangService ;
	
		@GetMapping("/customer/gio-hang-chi-tiet")
		public String layGioHangChiTiet( Model model) {
			 	GioHangDTO gioHangDTO = null ;
			 	String auth = SecurityContextHolder.getContext().getAuthentication().getName();
			 	KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth); 
 				 gioHangDTO = gioHangService.findByKhachHangId(khachHangDT0.getId());
				 if(gioHangDTO != null) {
					 if(gioHangDTO.getSoTienGiamGia() == null) {
						 gioHangDTO.setSoTienGiamGia(0);
						 gioHangDTO.setThanhTien(gioHangDTO.getTongTien());
					 }
				 }else {
					 gioHangDTO = new GioHangDTO();
					 gioHangDTO.setListGioHangChiTiets(null);
				 }
			 model.addAttribute("gioHangDTO",gioHangDTO);
			return "/customer/khach-hang/gio-hang-chi-tiet";
		}
		
		@GetMapping("/customer/gio-hang-chi-tiet/xoa-gio-hang")
		public String xoaGioHang(@RequestParam("id") Long id , Model model) {
					if(id != null) {
						gioHangChiTietService.deleteById(id);
						layGioHangChiTiet(model);
					}
					return "redirect:/customer/gio-hang-chi-tiet";
		}
		
		@GetMapping("/customer/gio-hang-chi-tiet/xoa-sach-gio-hang")
		public String xoaSachGioHang(Model model) {
						gioHangChiTietService.deleteAll();
						layGioHangChiTiet(model);
					return "redirect:/customer/gio-hang-chi-tiet";
		}
		
		@PostMapping("/customer/gio-hang-chi-tiet")
		public String capNhatGioHang(@RequestParam("ids") Long[] ids
												,@RequestParam("soLuongs") Integer[] soLuongs , Model model) {
			layGioHangChiTiet(model);
			for (Integer soLuong : soLuongs) {
				if(soLuong == null) {
					model.addAttribute("message","Số lượng trống !");
					return "/customer/khach-hang/gio-hang-chi-tiet";
				}else if(soLuong <= 0) {
					model.addAttribute("message","Số phải lớn hơn 0");
					return "/customer/khach-hang/gio-hang-chi-tiet";
				}
			}
			gioHangChiTietService.capNhatSoLuongGioHangChiTiet(ids,soLuongs);
			//Mốt là thay bằng spring security
			gioHangService.capNhatTongTien((long) 8);
			return "redirect:/customer/gio-hang-chi-tiet";
			
		}
		@PostMapping("/customer/gio-hang-chi-tiet/ap-dung-ma-giam-gia")
		public String apMaGiamGia(@ModelAttribute("gioHangDTO") GioHangDTO result , Model model) {
			//Thay bằng mã khách hàng
			GioHangDTO gioHangDTO = gioHangService.findByKhachHangId((long) 8);
			gioHangDTO.setMaGiamGia(result.getMaGiamGia());
			if(!gioHangDTO.getMaGiamGia().equals("")) {
				KhuyenMaiDTO khuyenMaiDTO = khuyenMaiService.timKhuyenMaiTheoTenKhuyenMai(result.getMaGiamGia());
				if(khuyenMaiDTO != null) {
					if(khuyenMaiDTO.isTrangThai() == false) {
						model.addAttribute("message_khuyenMai","Khuyến Mại đã hết hạn sử dụng !");
					}else {
						if(gioHangDTO.getTongTien() > khuyenMaiDTO.getGiaTriToiThieu()) {
							gioHangDTO.setSoTienGiamGia((khuyenMaiDTO.getPhanTramGiam()*gioHangDTO.getTongTien())/100);
							gioHangDTO.setThanhTien(gioHangDTO.getTongTien()-gioHangDTO.getSoTienGiamGia());
							model.addAttribute("message_khuyenMai_success","Áp dụng mã khuyến mãi thành công !");
						}else {
							model.addAttribute("message_khuyenMai","Giá trị tối thiểu của đơn phải lớn hơn "+khuyenMaiDTO.getGiaTriToiThieu()+" !");
						}
					}
				}else {
					model.addAttribute("message_khuyenMai","Mã khuyến mại không chính xác !");
				}
			}else {
				model.addAttribute("message_khuyenMai","Bạn chưa nhập mã !");
			}
			model.addAttribute("gioHangDTO",gioHangDTO);
			return "/customer/khach-hang/gio-hang-chi-tiet";
		}
//		
}
