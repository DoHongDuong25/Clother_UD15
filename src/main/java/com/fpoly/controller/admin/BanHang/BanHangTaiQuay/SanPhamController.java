package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dto.ChatLieuDTO;
import com.fpoly.dto.KichCoDTO;
import com.fpoly.dto.KieuDangDTO;
import com.fpoly.dto.LoaiSanPhamDTO;
import com.fpoly.dto.MauSacDTO;
import com.fpoly.dto.PhongCachDTO;
import com.fpoly.dto.composite.HinhAnhSanPhamChiTietDTO;
import com.fpoly.dto.composite.SPTaiQuayDTO;
import com.fpoly.dto.composite.ShowSanPhamTaiQuayDTO;
import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.HinhAnh;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.MauSac;
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

@Controller
@RequestMapping("/staff/product")
public class SanPhamController {
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
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ModelAttribute("lstMauSac")
	public List<MauSacDTO> getLstMauSac() {
		return mauSacService.selectAllMauSacExist().stream().map(item -> {
			MauSacDTO dto = new MauSacDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKieuDang")
	public List<KieuDangDTO> getLstKieuDang() {
		return kieuDangService.selectAllKieuDangExist().stream().map(item -> {
			KieuDangDTO dto = new KieuDangDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstChatLieu")
	public List<ChatLieuDTO> getLstChatLieu() {
		return chatLieuService.selectAllChatLieuExist().stream().map(item -> {
			ChatLieuDTO dto = new ChatLieuDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKichCo")
	public List<KichCoDTO> getLstKichCo() {
		return kichCoService.selectAllKichCoExist().stream().map(item -> {
			KichCoDTO dto = new KichCoDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstLoaiSanPham")
	public List<LoaiSanPhamDTO> getLstLoaiHang() {
		return loaiSanPhamService.selectAllLoaiHangExist().stream().map(item -> {
			LoaiSanPhamDTO dto = new LoaiSanPhamDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstPhongCach")
	public List<PhongCachDTO> getLstPhongCach() {
		return phongCachService.selectAllPhongCachExist().stream().map(item -> {
			PhongCachDTO dto = new PhongCachDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@GetMapping("")
	public String showSanPhamChiTiet(ModelMap model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		SPTaiQuayDTO resultSP = new SPTaiQuayDTO();
		List<ShowSanPhamTaiQuayDTO> lstSSPTQ = new ArrayList<>();
		
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			for (SanPham sp : resultPage.getContent()) {
				ShowSanPhamTaiQuayDTO ssptq = new ShowSanPhamTaiQuayDTO();
				List<Long> mauSacIds = sanPhamChiTietService.getLstMauSacBySanPhamId(sp.getId());
				List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getId(), mauSacIds);  
				List<String> lstHinhAnhStr = new ArrayList<>();
				for (HinhAnh ha : lstHinhAnh) {
					lstHinhAnhStr.add(ha.getTenAnh());
				}
				
				ssptq.setAnhChinhs(lstHinhAnhStr);
				ssptq.setSanPhamId(sp.getId());
				ssptq.setGia(sp.getGia());
				ssptq.setTenSanPham(sp.getTenSanPham());
				List<HinhAnhSanPhamChiTietDTO> lstHASPCT = new ArrayList<>();
				List<SanPhamChiTiet> lstSPCT = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sp.getId());
				for (SanPhamChiTiet spct : lstSPCT) {
					HinhAnhSanPhamChiTietDTO haspct = new HinhAnhSanPhamChiTietDTO();
					Optional<HinhAnh> optHA = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacId(sp.getId(), spct.getMauSac().getId());
					if (optHA.isPresent()) {
						haspct.setAnhChinh(optHA.get().getTenAnh());
					}
					haspct.setMauSacId(spct.getMauSac().getId());
					lstHASPCT.add(haspct);
				}
				ssptq.setHinhAnhSanPhamChiTietDTO(lstHASPCT);
				List<KichCo> lstKichCo = kichCoService.selectAllKichCoBySanPhamId(sp.getId());
				List<MauSac> lstMauSac = mauSacService.getAllMauSacExistBySPId(sp.getId());
				ssptq.setLstKichCo(lstKichCo);
				ssptq.setLstMauSac(lstMauSac);
				lstSSPTQ.add(ssptq);
			}
			resultSP.setLstShowSanPhamTaiQuayDTO(lstSSPTQ);
			model.addAttribute("dataSearch", dataSearch);
			model.addAttribute("resultSP", resultSP);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("sanPhamPage", resultPage);
		
		return "admin/product/banHangTaiQuay";
	}
	
	@PostMapping("/getData")
	public String showSanPhamChiTiet(ModelMap model, @ModelAttribute("resultSP") SPTaiQuayDTO dto) {
		System.out.println(dto.getMauSacId());
		System.out.println(dto.getKichCoId());
		return "redirect:/staff/product";
	}
	
	//test trang mau~, ko dung` den
//	@GetMapping("/a")
//	public String showSanPhamChiTietq(ModelMap model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
//		Page<SanPham> resultPage = null;
//		List<SPTaiQuayDTO> resultListSP = new ArrayList<>();
//		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
//		if (optDataSearch.isPresent()) {
//			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
//			for (SanPham sp : resultPage.getContent()) {
//				SPTaiQuayDTO sptq = new SPTaiQuayDTO();
//				List<Long> mauSacIds = sanPhamChiTietService.getLstMauSacBySanPhamId(sp.getId());
//				List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getId(), mauSacIds);  
//				List<String> lstHinhAnhStr = new ArrayList<>();
//				for (HinhAnh ha : lstHinhAnh) {
//					lstHinhAnhStr.add(ha.getTenAnh());
//				}
//				
//				sptq.setAnhChinhs(lstHinhAnhStr);
//				sptq.setSanPhamId(sp.getId());
//				sptq.setGia(sp.getGia());
//				sptq.setTenSanPham(sp.getTenSanPham());
//				List<HinhAnhSanPhamChiTietDTO> lstHASPCT = new ArrayList<>();
//				List<SanPhamChiTiet> lstSPCT = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sp.getId());
//				for (SanPhamChiTiet spct : lstSPCT) {
//					HinhAnhSanPhamChiTietDTO haspct = new HinhAnhSanPhamChiTietDTO();
//					Optional<HinhAnh> optHA = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacId(sp.getId(), spct.getMauSac().getId());
//					if (optHA.isPresent()) {
//						haspct.setAnhChinh(optHA.get().getTenAnh());
//					}
//					haspct.setMauSacId(spct.getMauSac().getId());
//					lstHASPCT.add(haspct);
//				}
//				sptq.setHinhAnhSanPhamChiTietDTO(lstHASPCT);
//				List<KichCo> lstKichCo = kichCoService.selectAllKichCoBySanPhamId(sp.getId());
//				List<MauSac> lstMauSac = mauSacService.getAllMauSacExistBySPId(sp.getId());
//				sptq.setLstKichCo(lstKichCo);
//				sptq.setLstMauSac(lstMauSac);
//				resultListSP.add(sptq);
//			}
//			model.addAttribute("dataSearch", dataSearch);
//			model.addAttribute("resultListSP", resultListSP);
//		}
//
//		int totalPages = resultPage.getTotalPages();
//		if (totalPages > 0) {
//			int start = Math.max(1, currentPage - 2);
//			int end = Math.min(currentPage + 2, totalPages);
//			if (totalPages > 5) {
//				if (end == totalPages) {
//					start = end - 5;
//				} else if (start == 1) {
//					end = start + 5;
//				}
//			}
//			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		model.addAttribute("sanPhamPage", resultPage);
//		return "customer/home/home";
//	}
}
