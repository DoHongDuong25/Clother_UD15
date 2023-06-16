package com.fpoly.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.KhachHang;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.service.KhachHangService;

@Controller(value="KhachHangControllerOfAdmin")
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
	@Autowired
	private KhachHangService khachHangService ;
	
	@Autowired
	private KhachHangRepository khachHangRepository ;
	
	
	@RequestMapping("/danh-sach")
	public String layTatCa(Model model , HttpServletRequest request ) {
		List<KhachHangDTO> listkhachHangDTO = khachHangService.findAll();
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		model.addAttribute("listKhachHangDTO",listkhachHangDTO);
		return  "redirect:/admin/khach-hang/danh-sach/1";
	}
	
	
	
	
	@RequestMapping("/danh-sach/{pageNumber}")
	public String layDanhSach( 
			@PathVariable(name="pageNumber") Integer currentPage ,
			@RequestParam(name="soDienThoai",required=false) String soDienThoai,
			@RequestParam(name="trangThai",required=false ) Integer trangThai,
			Model model , HttpServletRequest request ) {
		
		
		Pageable pageable = PageRequest.of(currentPage-1,5);
		Page<KhachHang> page = khachHangRepository.findAll(pageable);
		
		List<KhachHangDTO> listkhachHangDTO = khachHangService.findAll(pageable);
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("currentPage",currentPage);
		
		
		model.addAttribute("soDienThoai",soDienThoai);
		model.addAttribute("trangThai",trangThai);
		model.addAttribute("listKhachHangDTO",listkhachHangDTO);
		return  "/admin/khach-hang/danhSach";
	}
	
	
	@PostMapping("/danh-sach")
	public String locDanhSach(@RequestParam(name="trangThai",required=false ) Integer trangThai ,
			Model model , HttpServletRequest request ) {
			if(trangThai == 2) {
				return  "redirect:/admin/khach-hang/danh-sach/1";
			}else {
				List<KhachHangDTO> listkhachHangDTO = khachHangService.locDanhSachTheoTrangThai(trangThai);
				String message = request.getParameter("message");
				model.addAttribute("message",message);
				model.addAttribute("listKhachHangDTO",listkhachHangDTO);
				return  "redirect:/admin/khach-hang/danh-sach/chuyen-doi-trang-thai/1?trangThai="+trangThai;
			}
	}
	
	
	
	
	@RequestMapping("/danh-sach/chuyen-doi-trang-thai/{pageNumber}")
	public String capNhatTrangThai(
									@RequestParam(name="trangThai",required=false) Integer trangThai ,
									@RequestParam(name="soDienThoai",required=false) String soDienThoai ,
									@PathVariable(name="pageNumber") Integer currentPage ,
			Model model , HttpServletRequest request) {
		
		Pageable pageable = PageRequest.of(currentPage-1,5);
		Page<KhachHang> page = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai,pageable);
		List<KhachHangDTO> listkhachHangDTO = khachHangService.findAllByTrangThaiCoPhanTrang(trangThai,pageable);
		String message = request.getParameter("message");
		model.addAttribute("listKhachHangDTO",listkhachHangDTO);
		
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("currentPage",currentPage);
		
		
		model.addAttribute("soDienThoai",soDienThoai);
		model.addAttribute("trangThai",trangThai);
		model.addAttribute("message",message);
		return  "/admin/khach-hang/danhSach";
	}
	
	@RequestMapping("danh-sach/chinh-sua")
	public ModelAndView chinhSuaKhachHangForm(
			@RequestParam(value="id",required=false) Long id ,
			@RequestParam(value="email",required=false) String email ,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/khach-hang/chinhSua");
		KhachHangDTO model = new KhachHangDTO();
		String message = request.getParameter("message");
		if(id != null) {
			model = khachHangService.findById(id);
		}
		mav.addObject("message",message);
		mav.addObject("model",model);
		mav.addObject("email",email);
		return mav ;
	}
	
	
	@RequestMapping("danh-sach/tim-kiem/{pageNumber}")
	public ModelAndView timKiemKhachHangTheoSoDienThoai(@RequestParam(name="soDienThoai",required=false) String soDienThoai 
			,@PathVariable(name="pageNumber") Integer currentPage  ) {
		ModelAndView mav = new ModelAndView("/admin/khach-hang/danhSach");
		Pageable pageable = PageRequest.of(currentPage-1,2);
		Page<KhachHang> page = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai,pageable);
		List<KhachHangDTO> listKhachHangDTO = khachHangService.findAllBySoDienThoaiCoPhanTrang(soDienThoai,pageable);
		
		mav.addObject("totalPages",page.getTotalPages());
		mav.addObject("totalItems",page.getTotalElements());
		mav.addObject("currentPage",currentPage);
		
		mav.addObject("soDienThoai",soDienThoai);
		mav.addObject("listKhachHangDTO",listKhachHangDTO);
		
		return mav ;
	}
	
	
	
	
}
