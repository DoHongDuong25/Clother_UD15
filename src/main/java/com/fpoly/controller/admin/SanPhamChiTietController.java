package com.fpoly.controller.admin;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fpoly.dto.ChatLieuDTO;
import com.fpoly.dto.KichCoDTO;
import com.fpoly.dto.KieuDangDTO;
import com.fpoly.dto.LoaiSanPhamDTO;
import com.fpoly.dto.MauSacDTO;
import com.fpoly.dto.PhongCachDTO;
import com.fpoly.dto.composite.SanPhamManageDTO;
import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.ChatLieu;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.KieuDang;
import com.fpoly.entity.LoaiSanPham;
import com.fpoly.entity.MauSac;
import com.fpoly.entity.PhongCach;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.service.ChatLieuService;
import com.fpoly.service.KichCoService;
import com.fpoly.service.KieuDangService;
import com.fpoly.service.LoaiSanPhamService;
import com.fpoly.service.MauSacService;
import com.fpoly.service.PhongCachService;
import com.fpoly.service.SanPhamChiTietService;
import com.fpoly.service.SanPhamService;


@Controller
@RequestMapping("admin/product")
public class SanPhamChiTietController {
	@Autowired
	private SanPhamChiTietService sanPhamChiTietService;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private MauSacService mauSacService;
	
	@Autowired
	private ChatLieuService chatLieuService;
	
	@Autowired
	private KichCoService kichCoService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private PhongCachService phongCachService;
	
	@Autowired
	private KieuDangService kieuDangService;
	
	@ModelAttribute("lstMauSac")
	public List<MauSacDTO> getLstMauSac(){
		return mauSacService.selectAllMauSacExist().stream().map(item->{
			MauSacDTO dto = new MauSacDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("lstKieuDang")
	public List<KieuDangDTO> getLstKieuDang(){
		return kieuDangService.selectAllKieuDangExist().stream().map(item->{
			KieuDangDTO dto = new KieuDangDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("lstChatLieu")
	public List<ChatLieuDTO> getLstChatLieu(){
		return chatLieuService.selectAllChatLieuExist().stream().map(item->{
			ChatLieuDTO dto = new ChatLieuDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("lstKichCo")
	public List<KichCoDTO> getLstKichCo(){
		return kichCoService.selectAllKichCoExist().stream().map(item->{
			KichCoDTO dto = new KichCoDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("lstLoaiSanPham")
	public List<LoaiSanPhamDTO> getLstLoaiHang(){
		return loaiSanPhamService.selectAllLoaiHangExist().stream().map(item->{
			LoaiSanPhamDTO dto = new LoaiSanPhamDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@ModelAttribute("lstPhongCach")
	public List<PhongCachDTO> getLstPhongCach(){
		return phongCachService.selectAllPhongCachExist().stream().map(item->{
			PhongCachDTO dto = new PhongCachDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("")
	public String productManage(ModelMap model) {
		List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
		model.addAttribute("sanPhamChiTiets", result);
		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		return "admin/product/productManage";
	}
	
	@GetMapping("add")
	public String addProductDetail(ModelMap model) {
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		return "admin/product/addOrEdit";
	}
	
	@GetMapping("genData")
	public String genData(ModelMap model, @RequestParam("d") Long id) {
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(id);
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		model.addAttribute("dataGen", dataGen);
		return "admin/product/addOrEdit";
	}
	
	@PostMapping("generateProductDetails")
	public ModelAndView generateProductDetails(ModelMap model,
			@Validated @ModelAttribute("SanPhamManageDTO") SanPhamManageDTO data,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("sanPhamManageDTO", data);
			return new ModelAndView("admin/product/addOrEdit", model);
		}
		SanPham sanPham = new SanPham();
		sanPham.setDaXoa(false);
		sanPham.setGiaHienHanh(data.getGiaHienHanh());
		sanPham.setTenSanPham(data.getTenSanPham());	
		
		sanPham.setMoTa("");
		
		ChatLieu chatLieu = new ChatLieu();
		chatLieu.setId(data.getChatLieuId());;
		sanPham.setChatLieu(chatLieu);
		
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		loaiSanPham.setId(data.getLoaiSanPhamId());
		sanPham.setLoaiSanPham(loaiSanPham);
		
		PhongCach phongCach = new PhongCach();
		phongCach.setId(data.getPhongCachId());
		sanPham.setPhongCach(phongCach);
		
		KieuDang kieuDang = new KieuDang();
		kieuDang.setId(data.getKieuDangId());
		sanPham.setKieuDang(kieuDang);
		
		sanPhamService.save(sanPham);
		
		for (Long kichCoId : data.getKichCoIds()) {
			for (Long mauSacId : data.getMauSacIds()) {
				SanPhamChiTiet spct = new SanPhamChiTiet();
				spct.setCoHienThi(true);
				spct.setDaXoa(false);
				spct.setSanPham(sanPham);
				
				KichCo kichCo = new KichCo();
				kichCo.setId(kichCoId);
				spct.setKichCo(kichCo);
				
				MauSac mauSac = new MauSac();
				mauSac.setId(mauSacId);
				spct.setMauSac(mauSac);
				
				spct.setSoLuong(0);
				sanPhamChiTietService.save(spct);
			}
		}
		return new ModelAndView("redirect:/admin/product/genData?d=" + sanPham.getId(), model);
	}
	
	@GetMapping("changeIsShow/{id}/{status}")
	public String changeIsShow(ModelMap model, @PathVariable("id") Long id,
			@PathVariable("status") Boolean status) {
		Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(id);
		if(opt.isPresent()) {
			opt.get().setCoHienThi(status);
			sanPhamChiTietService.save(opt.get());
			model.addAttribute("messageSuccess", "Sửa trạng thái hiển thị của sản phẩm thành công");
		}
		List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
		model.addAttribute("sanPhamChiTiets", result);
		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		return "admin/product/productManage";
	}
	
	@GetMapping("changeIsShowFormAddOrEdit/{id}/{status}")
	public ModelAndView changeIsShowFormAddOrEdit(ModelMap model, @PathVariable("id") Long id,
			@PathVariable("status") Boolean status) {
		Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(id);
		if(opt.isPresent()) {
			opt.get().setCoHienThi(status);
			sanPhamChiTietService.save(opt.get());
			model.addAttribute("messageSuccess", "Sửa trạng thái hiển thị của sản phẩm thành công");
		}else model.addAttribute("messageDanger", "Sửa trạng thái hiển thị của sản phẩm thất bại");
		return new ModelAndView("redirect:/admin/product/genData?d=" + opt.get().getSanPham().getId());
	}
	
	@GetMapping("searchProductManage")
	public String searchProductManage(ModelMap model, @ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch) {
		Optional<SPAndSPCTSearchDto> data = Optional.of(dataSearch);
		if(data.isPresent()) {
			List<SanPhamChiTiet> result = sanPhamChiTietService.searchProductDetailExist(data.get());
			model.addAttribute("sanPhamChiTiets", result);
			model.addAttribute("dataSearch", dataSearch);
			if(result.size() == 0) {
				model.addAttribute("messageDanger", "Không tìm thấy sản phẩm nào đủ điều kiện trên");
			}
			return "admin/product/productManage";
		}else {
			List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
			model.addAttribute("sanPhamChiTiets", result);
			model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
			return "admin/product/productManage";
		}
	}
}
