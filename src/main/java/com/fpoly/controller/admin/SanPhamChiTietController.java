package com.fpoly.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.expression.Fields;

import com.fpoly.dto.ChatLieuDTO;
import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.dto.KichCoDTO;
import com.fpoly.dto.KieuDangDTO;
import com.fpoly.dto.LoaiSanPhamDTO;
import com.fpoly.dto.MauSacDTO;
import com.fpoly.dto.PhongCachDTO;
import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.dto.composite.HinhAnhSanPhamChiTietDTO;
import com.fpoly.dto.composite.SanPhamManageDTO;
import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.ChatLieu;
import com.fpoly.entity.HinhAnh;
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
import com.fpoly.service.StorageService;

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
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
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
		return "admin/product/addProduct";
	}
	
	@GetMapping("genData")
	public String genData(ModelMap model, @RequestParam("d") Long id) {
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(id);
		model.addAttribute("dataGen", dataGen);		
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		model.addAttribute("hinhAnhDTO", new HinhAnhDTO());
		Optional<Long> idL = Optional.of(id);
		if(idL.isPresent()) {
			if(!idL.isEmpty()) {
				model.addAttribute("messageSuccess", "Thêm sản phẩm chi tiết thành công");
			}else model.addAttribute("messageSuccess", "Thêm sản phẩm chi tiết thất bại");
		}else model.addAttribute("messageSuccess", "Thêm sản phẩm chi tiết thất bại");
		return "admin/product/addProduct";
	}
	
	@PostMapping("generateProductDetails")
	public ModelAndView generateProductDetails(ModelMap model,
			@Valid @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("sanPhamManageDTO", data);
			return new ModelAndView("admin/product/addProduct", model);
		}
		SanPham sanPham = new SanPham();
		sanPham.setDaXoa(false);
		sanPham.setGia(data.getGia());
		sanPham.setTenSanPham(data.getTenSanPham());	
		sanPham.setMoTa(data.getMoTa());
		
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
				int soLuong = data.getSoLuong();
				spct.setSoLuong(soLuong);
				String maSPCT = UUID.randomUUID().toString();
				spct.setMaSanPhamChiTiet(maSPCT);
				
				KichCo kichCo = new KichCo();
				kichCo.setId(kichCoId);
				spct.setKichCo(kichCo);
				
				MauSac mauSac = new MauSac();
				mauSac.setId(mauSacId);
				spct.setMauSac(mauSac);
				
				sanPhamChiTietService.save(spct);
			}
		}
		model.addAttribute("sanPhamManageDTO", data);
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
			List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
			model.addAttribute("sanPhamChiTiets", result);
			model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
			return "admin/product/productManage";
		}else {
			model.addAttribute("messageSuccess", "Không tìm thấy sản phẩm!");
			List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
			model.addAttribute("sanPhamChiTiets", result);
			model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
			return "admin/product/productManage";
		}
	}
	
	@GetMapping("changeIsShowFormAddProduct/{id}/{status}")
	public ModelAndView changeIsShowFormAddProduct(ModelMap model, @PathVariable("id") Long id,
			@PathVariable("status") Boolean status) {
		Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(id);
		if(opt.isPresent()) {
			opt.get().setCoHienThi(status);
			sanPhamChiTietService.save(opt.get());
			model.addAttribute("messageSuccess", "Sửa trạng thái hiển thị của sản phẩm thành công");
		}else model.addAttribute("messageDanger", "Sửa trạng thái hiển thị của sản phẩm thất bại");
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(opt.get().getSanPham().getId());
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		return new ModelAndView("admin/product/addProduct", model);
	}
	
	@GetMapping("searchProductManage")
	public String searchProductManage(ModelMap model, @Valid @ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch,
			BindingResult result) {
		if(result.hasErrors()) {
			List<SanPhamChiTiet> resultSPCT = sanPhamChiTietService.getLstSanPhamChiTietExist();
			model.addAttribute("sanPhamChiTiets", resultSPCT);
			return "admin/product/productManage";
		}
		this.showViewBeforeSearch(model, dataSearch);
		return "admin/product/productManage";
	}
	
	@PostMapping("deleteAllByIdsProductManage")
	public ModelAndView deleteAllByIdProductManage(ModelMap model, @ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.deleteAllByIds(model, request, response);
		this.showViewBeforeSearch(model, dataSearch);
		return new ModelAndView("admin/product/productManage", model);
	}
	
	@PostMapping("deleteAllByIdsAddProduct")
	public ModelAndView deleteAllByIdAddProduct(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.deleteAllByIds(model, request, response);
		return new ModelAndView("admin/product/addProduct", model);
	}
	
	@PostMapping("updateQuantityProductDetail")
	public ModelAndView updateQuantityProductDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] soLuongs = request.getParameterValues("soLuongs");
		String[] ids = request.getParameterValues("soLuongTheoIds");
		if(soLuongs != null && ids != null) {
			for(String item: soLuongs) {
				if(!isNumeric(item)) {
					model.addAttribute("messageDanger", "Số lượng phải là số");
					return new ModelAndView("admin/product/addProduct", model);
				}
			}
			for(String item: ids) {
				if(!isNumeric(item)) {
					model.addAttribute("messageDanger", "Id phải là số");
					return new ModelAndView("admin/product/addProduct", model);
				}
			}
		}
		//add key-id, value-soLuong -> map
		Map<String, String> hm = new HashMap<>();
		for(int i=0; i<ids.length;i++) {
			hm.put(ids[i], soLuongs[i]);
		}
		Long sPID = null;
		for (Map.Entry<String, String> mapItem : hm.entrySet()) {
	        Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(Long.parseLong(mapItem.getKey()));
	        if(opt.isPresent()) {
	        	opt.get().setSoLuong(Integer.parseInt(mapItem.getValue()));
	        	sPID = opt.get().getSanPham().getId();
	        	sanPhamChiTietService.save(opt.get());
	        	model.addAttribute("messageSuccess", "Cập nhật số lượng thành công");
	        }else model.addAttribute("messageSuccess", "Cập nhật số lượng thất bại sản phẩm có tên: "+ opt.get().getSanPham().getTenSanPham());
	    }
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sPID);
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		return new ModelAndView("admin/product/addProduct", model);
	}
	
	
	
	@GetMapping("addImageProductDetail")
	public String addImageProductDetail(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("productIds");
		if(ids != null) {
			List<SanPhamChiTiet> lstSPCTAddImg = new ArrayList<>();
			long idSanPham = 0;
			for(String item: ids) {
				Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(Long.parseLong(item));
				if(opt.isPresent()) {
					idSanPham = opt.get().getSanPham().getId();
					lstSPCTAddImg.add(opt.get());					
				}
			}
			HinhAnhSanPhamChiTietDTO hinhAnhSanPhamChiTietDTO = new HinhAnhSanPhamChiTietDTO();
			hinhAnhSanPhamChiTietDTO.setIsOpenPopupAddImage(true);
			List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(idSanPham);
			model.addAttribute("dataGen", dataGen);
			model.addAttribute("lstSPCTAddImg", lstSPCTAddImg);
			model.addAttribute("hinhAnhSanPhamChiTietDTO", hinhAnhSanPhamChiTietDTO);
			model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		}else {
			model.addAttribute("messageDanger", "Bạn chưa chọn ô checkbox nào");
			model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
//			List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sPID);
//			model.addAttribute("dataGen", dataGen);
			return "admin/product/productManage";
		}
		return "admin/product/addProduct";
	}
	
	@PostMapping("saveImageProductDetail")
	public String saveImageProductDetail(ModelMap model, @ModelAttribute("hinhAnhSanPhamChiTietDTO") List<HinhAnhSanPhamChiTietDTO> dtos,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("imageTheoIds");
		return "admin/product/addProduct";
	}
	
//	public String saveImageProductDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		return "admin/product/productManage";
//	}
	
	public void deleteAllByIds(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("productIds");
		if(ids != null) {
			for(String item: ids) {
				Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(Long.parseLong(item));
				if(opt.isPresent()) {
//						if(!StringUtils.isEmpty(opt.get().getImage())) {
//							storageService.delete(opt.get().getImage());
//						}
					sanPhamChiTietService.delete(opt.get());
				}
			}
			model.addAttribute("messageSuccess", "Đã xóa hết những sản phẩm đã chọn");
		}else model.addAttribute("messageDanger", "Bạn chưa chọn ô checkbox nào");
	}
	
	public void showViewBeforeSearch(ModelMap model, @ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch) {
		Optional<SPAndSPCTSearchDto> data = Optional.of(dataSearch);
		if(data.isPresent()) {
			List<SanPhamChiTiet> resultSPCT = sanPhamChiTietService.searchProductDetailExist(data.get());
			model.addAttribute("sanPhamChiTiets", resultSPCT);
			model.addAttribute("dataSearch", dataSearch);
		}else {
			List<SanPhamChiTiet> resultSPCT = sanPhamChiTietService.getLstSanPhamChiTietExist();
			model.addAttribute("sanPhamChiTiets", resultSPCT);
			model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		}
	}
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	
}

