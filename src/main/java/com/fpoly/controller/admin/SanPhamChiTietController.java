package com.fpoly.controller.admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
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

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.constant.MauSacContants;
import com.fpoly.constant.OptionContants;
import com.fpoly.dto.ChatLieuDTO;
import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.dto.KichCoDTO;
import com.fpoly.dto.KieuDangDTO;
import com.fpoly.dto.LoaiSanPhamDTO;
import com.fpoly.dto.MauSacDTO;
import com.fpoly.dto.PhongCachDTO;
import com.fpoly.dto.composite.HinhAnhMauSacDTO;
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
import com.fpoly.service.HinhAnhService;
import com.fpoly.service.KichCoService;
import com.fpoly.service.KieuDangService;
import com.fpoly.service.LoaiSanPhamService;
import com.fpoly.service.MauSacService;
import com.fpoly.service.PhongCachService;
import com.fpoly.service.SanPhamChiTietService;
import com.fpoly.service.SanPhamService;
import com.fpoly.service.StorageService;
//import com.fpoly.service.impl.ProductDetailsWithColorSizeRepository;


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
	
	@Autowired
	private HinhAnhService hinhAnhService;
	
//	private ProductDetailsWithColorSizeRepository productDetailsWithColorSizeRepository;
	
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
		List<SanPham> resultSP = sanPhamService.getSanPhamExist();
		model.addAttribute("sanPhams", resultSP);
		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		return "admin/product/productManage";
	}
	
	@GetMapping("add")
	public String addProductDetail(ModelMap model) {
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		return "admin/product/addProduct";
	}
	
	@PostMapping("generateProductDetails")
	public String generateProductDetails(ModelMap model,
			@Valid @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("sanPhamManageDTO", data);
			return "admin/product/addProduct";
		}else {
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
			data.setIsEdit(true);
			List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sanPham.getId());
			dataGen.forEach(i->{
				Optional<MauSac> optMS = mauSacService.findById(i.getMauSac().getId());
				Optional<KichCo> optKC = kichCoService.findById(i.getKichCo().getId());
				i.setMauSac(optMS.get());
				i.setKichCo(optKC.get());
			});
			model.addAttribute("dataGen", dataGen);	
			model.addAttribute("sanPhamManageDTO", data);
			return "/admin/product/addProduct";
		}
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
	}//sua lai
	
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
			List<SanPham> resultSP = sanPhamService.getSanPhamExist();
			model.addAttribute("sanPhams", resultSP);
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
	public ModelAndView updateQuantityProductDetail(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
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
	        	data.setIsEdit(true);
	        	model.addAttribute("messageSuccess", "Cập nhật số lượng thành công");
	        }else model.addAttribute("messageSuccess", "Cập nhật số lượng thất bại sản phẩm có tên: "+ opt.get().getSanPham().getTenSanPham());
	    }
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sPID);
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", data);
		return new ModelAndView("admin/product/addProduct", model);
	}
	
	
	
	@GetMapping("addImageProductDetail")
	public String addImageProductDetail(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] spctIds = request.getParameterValues("mauSacProductIds");
		long idSanPham = 0;
		data.setIsEdit(true);
		if(spctIds != null) {
			for(String id: spctIds) {
				if(isNumeric(id)) {
					idSanPham = Long.parseLong(id);
					break;
				}
			}
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(idSanPham);	
			
			List<HinhAnhMauSacDTO> lsthinhAnhMauSacDTO = new ArrayList<HinhAnhMauSacDTO>();
			for (MauSac mauSac : lstMauSacAddImg) {
				HinhAnhMauSacDTO dto = new HinhAnhMauSacDTO();
				dto.setMauSacAddImagesId(mauSac.getId());
				dto.setTenMauSacAddImg(mauSac.getTenMauSac());
				lsthinhAnhMauSacDTO.add(dto);
			}
			data.setLstHinhAnhMauSacDTO(lsthinhAnhMauSacDTO);
		}
		data.setIsCreatedImg(true);
		model.addAttribute("sanPhamManageDTO", data);
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(idSanPham);
		model.addAttribute("dataGen", dataGen);
		return "admin/product/addProduct";
	}
	
	@PostMapping("saveImageProductDetail")
	public String saveImageProductDetail(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO) {
		sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().filter(m -> !m.getImgFiles().isEmpty()).forEach(i ->{
			Optional<MauSac> optMS = mauSacService.findById(i.getMauSacAddImagesId());
			sanPhamManageDTO.setIsEdit(true);
			i.getImgFiles().stream().filter(img1 -> !img1.isEmpty()).forEach(img -> {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				HinhAnh hinhAnh = new HinhAnh();
				hinhAnh.setTenAnh(storageService.getStoredFileName(img, uuString));
				storageService.store(img, hinhAnh.getTenAnh());
				if(optMS.isPresent()) {
					hinhAnh.setMauSac(optMS.get());
					hinhAnhService.save(hinhAnh);
					model.addAttribute("messageSuccess", "Hoàn tất thành công thêm sản phẩm");
				}else {
					model.addAttribute("messageDanger", "Thêm hình ảnh cho sản phẩm có màu: " + i.getMauSacAddImagesId()+" thất bại");		
				}});
			});
		List<SanPhamChiTiet> result = sanPhamChiTietService.getLstSanPhamChiTietExist();
		model.addAttribute("sanPhamChiTiets", result);
		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		return "admin/product/productManage";
	}
	
	@PostMapping("saveOptionValue")
	public String saveOptionValue(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] value = request.getParameterValues("thuocTinhInput");
		String[] option = request.getParameterValues("fieldthuocTinhInput");
		if(value != null && option!= null) {
			if(!option[0].isEmpty() && !value[0].isEmpty()) {
				if(option[0].equalsIgnoreCase(OptionContants.chatLieu)) {
					ChatLieu entity = new ChatLieu();
					entity.setTenChatLieu(value[0].toString());
					chatLieuService.save(entity);
					List<ChatLieu> loadData = chatLieuService.selectAllChatLieuExist();
					model.addAttribute("lstChatLieu", loadData);
					model.addAttribute("messageSuccess", "Thêm mới chất liệu thành công");
				}else if(option[0].equalsIgnoreCase(OptionContants.loaiSanPham)){
					LoaiSanPham entity = new LoaiSanPham();
					entity.setTenLoaiSanPham(value[0].toString());
					loaiSanPhamService.save(entity);
					List<LoaiSanPham> loadData = loaiSanPhamService.selectAllLoaiHangExist();
					model.addAttribute("lstLoaiSanPham", loadData);
					model.addAttribute("messageSuccess", "Thêm mới loại sản phẩm thành công");
				}else if(option[0].equalsIgnoreCase(OptionContants.kichCo)){
					KichCo entity = new KichCo();
					entity.setTenKichCo(value[0].toString());
					kichCoService.save(entity);
					List<KichCo> loadData = kichCoService.selectAllKichCoExist();
					model.addAttribute("lstKichCo", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kích cỡ thành công");
				}else if(option[0].equalsIgnoreCase(OptionContants.kieuDang)){
					KieuDang entity = new KieuDang();
					entity.setTenKieuDang(value[0].toString());
					kieuDangService.save(entity);
					List<KieuDang> loadData = kieuDangService.selectAllKieuDangExist();
					model.addAttribute("lstKieuDang", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kiểu dáng thành công");
				}else if(option[0].equalsIgnoreCase(OptionContants.phongCach)){
					PhongCach entity = new PhongCach();
					entity.setTenPhongCach(value[0].toString());
					phongCachService.save(entity);
					List<PhongCach> loadData = phongCachService.selectAllPhongCachExist();
					model.addAttribute("lstPhongCach", loadData);
					model.addAttribute("messageSuccess", "Thêm mới phong cách thành công");
				}else if(option[0].equalsIgnoreCase(OptionContants.mauSac)){
					MauSac entity = new MauSac();
					entity.setTenMauSac(value[0].toString());
					mauSacService.save(entity);
					List<MauSac> loadData = mauSacService.selectAllMauSacExist();
					model.addAttribute("lstMauSac", loadData);
					model.addAttribute("messageSuccess", "Thêm mới màu sắc thành công");
				}
			}else {
				model.addAttribute("messageDanger", "Tên giá trị thuộc tính không được để trống");
			}
		}else {
			model.addAttribute("messageDanger", "Lưu giá trị thuộc tính sản phẩm thất bại");
		}
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		return "admin/product/addProduct";
	}

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
//			List<SanPhamChiTiet> resultSPCT = sanPhamChiTietService.searchProductDetailExist(data.get());
			List<SanPham> resultSP = sanPhamService.searchProductExist(dataSearch);
			model.addAttribute("sanPhams", resultSP);
			model.addAttribute("dataSearch", dataSearch);
		}else {
//			List<SanPhamChiTiet> resultSPCT = sanPhamChiTietService.getLstSanPhamChiTietExist();
			List<SanPham> resultSP = sanPhamService.getSanPhamExist();
			model.addAttribute("sanPhams", resultSP);
			model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		}
	}
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	
}

