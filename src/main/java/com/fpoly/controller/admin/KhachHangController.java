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

import com.fpoly.dto.DiaChiDTO;
import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.KhachHang;
import com.fpoly.service.DiaChiService;
import com.fpoly.service.KhachHangService;

@Controller(value="KhachHangControllerOfAdmin")
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
	@Autowired
	private KhachHangService khachHangService ;
	
	@Autowired
	private DiaChiService diaChiService ;
	
	
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
			@PathVariable(name="pageNumber") Integer page ,
			@RequestParam(name="soDienThoai",required=false) String soDienThoai,
			@RequestParam(name="trangThai",required=false ) Integer trangThai,
			Model model , HttpServletRequest request ) {
		KhachHangDTO dto = new KhachHangDTO();
		dto.setPage(page);
		Pageable pageable = PageRequest.of(page-1,5);
		dto.setListKhachHangDTO(khachHangService.findAll(pageable));
		dto.setTotalItems((int) khachHangService.countAll());
		dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/5));
		
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		
		
		model.addAttribute("soDienThoai",soDienThoai);
		model.addAttribute("trangThai",trangThai);
		
		model.addAttribute("khachHangDTO",dto);
		return  "/admin/khach-hang/danhSach";
	}
	
	
	@PostMapping("/danh-sach")
	public String locDanhSach(@RequestParam(name="trangThai",required=false ) Integer trangThai ,
			Model model , HttpServletRequest request ) {
			KhachHangDTO dto = new KhachHangDTO();
			if(trangThai == 2) {
				return  "redirect:/admin/khach-hang/danh-sach/1";
			}else {
				dto.setListKhachHangDTO(khachHangService.locDanhSachTheoTrangThai(trangThai));
				String message = request.getParameter("message");
				model.addAttribute("message",message);
				model.addAttribute("khachHangDTO",dto);
				return  "redirect:/admin/khach-hang/danh-sach/chuyen-doi-trang-thai/1?trangThai="+trangThai;
			}
	}
	
	
	
	
	@RequestMapping("/danh-sach/chuyen-doi-trang-thai/{pageNumber}")
	public String capNhatTrangThai(
									@RequestParam(name="trangThai",required=false) Integer trangThai ,
									@RequestParam(name="soDienThoai",required=false) String soDienThoai ,
									@PathVariable(name="pageNumber") Integer currentPage ,
			Model model , HttpServletRequest request) {
		String message = request.getParameter("message");
		KhachHangDTO dto = new KhachHangDTO();
		dto.setPage(currentPage);
		
		
		Pageable pageable = PageRequest.of(currentPage-1,5);
		dto.setListKhachHangDTO(khachHangService.findAllByTrangThaiCoPhanTrang(trangThai,pageable));
		dto.setTotalItems((int) khachHangService.countByTrangThai(trangThai));
		dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/5));
		
		model.addAttribute("khachHangDTO",dto);
		
		model.addAttribute("soDienThoai",soDienThoai);
		model.addAttribute("trangThai",trangThai);
		model.addAttribute("message",message);
		return  "/admin/khach-hang/danhSach";
	}
	
	@RequestMapping("danh-sach/chinh-sua")
	public ModelAndView chinhSuaKhachHangForm(
			@RequestParam(value="page",required=true) Integer page,
			@RequestParam(value="id",required=false) Long id ,
			@RequestParam(value="email",required=false) String email ,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/khach-hang/chinhSua");
		String message = request.getParameter("message");
		KhachHangDTO model = new KhachHangDTO();
		DiaChiDTO diaChiDTO = new DiaChiDTO();
		diaChiDTO.setPage(page);
		
		Pageable pageable = PageRequest.of(page-1,6);
		if(id != null) {
			model = khachHangService.findById(id);
 			diaChiDTO.setListDiaChi(diaChiService.findAllDiaChiByMaKhachHang(model.getId(),pageable));
			diaChiDTO.setTotalItems((int) diaChiService.countByMaKhachHang(model.getId()));
			diaChiDTO.setTotalPages((int) Math.ceil((double)diaChiDTO.getTotalItems()/6));
			
		}
		mav.addObject("message",message);
		mav.addObject("model",model);
		mav.addObject("diaChiDTO",diaChiDTO);
		mav.addObject("email",email);
		return mav ;
	}
	
	
	
	@RequestMapping("danh-sach/tim-kiem/{pageNumber}")
	public ModelAndView timKiemKhachHangTheoSoDienThoai(
			@RequestParam(name="soDienThoai",required=false) String soDienThoai 
			,@RequestParam(name="trangThai",required=false) Integer trangThai 
			,@PathVariable(name="pageNumber") Integer currentPage  ) {
		ModelAndView mav = new ModelAndView("/admin/khach-hang/danhSach");
		
		KhachHangDTO dto = new KhachHangDTO();
		dto.setPage(currentPage);
		
		
		Pageable pageable = PageRequest.of(currentPage-1,5);
		dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiCoPhanTrang(soDienThoai,pageable));
		dto.setTotalItems((int) khachHangService.countBySoDienThoai(soDienThoai));
		dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/5));
		
		mav.addObject("khachHangDTO",dto);
		mav.addObject("soDienThoai",soDienThoai);
		mav.addObject("trangThai",trangThai);
		return mav ;
	}
	
	
	
	
}
