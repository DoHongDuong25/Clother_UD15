package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

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
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.MauSac;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.*;
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
import com.fpoly.service.banHangService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BanHangController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private banHangService banHangService;

    @Autowired
    private banHangRepository banHangRepository;


    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

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

//	@GetMapping("")
//	public String showSanPhamChiTiet(ModelMap model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
//		Page<SanPham> resultPage = null;
//		SPTaiQuayDTO resultSP = new SPTaiQuayDTO();
//		List<ShowSanPhamTaiQuayDTO> lstSSPTQ = new ArrayList<>();
//		
//		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
//		if (optDataSearch.isPresent()) {
//			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
//			for (SanPham sp : resultPage.getContent()) {
//				ShowSanPhamTaiQuayDTO ssptq = new ShowSanPhamTaiQuayDTO();
//				List<Long> mauSacIds = sanPhamChiTietService.getLstMauSacBySanPhamId(sp.getId());
//				List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getId(), mauSacIds);  
//				List<String> lstHinhAnhStr = new ArrayList<>();
//				for (HinhAnh ha : lstHinhAnh) {
//					lstHinhAnhStr.add(ha.getTenAnh());
//				}
//				
//				ssptq.setAnhChinhs(lstHinhAnhStr);
//				ssptq.setSanPhamId(sp.getId());
//				ssptq.setGia(sp.getGia());
//				ssptq.setTenSanPham(sp.getTenSanPham());
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
//				ssptq.setHinhAnhSanPhamChiTietDTO(lstHASPCT);
//				List<KichCo> lstKichCo = kichCoService.selectAllKichCoBySanPhamId(sp.getId());
//				List<MauSac> lstMauSac = mauSacService.getAllMauSacExistBySPId(sp.getId());
//				ssptq.setLstKichCo(lstKichCo);
//				ssptq.setLstMauSac(lstMauSac);
//				lstSSPTQ.add(ssptq);
//			}
//			resultSP.setLstShowSanPhamTaiQuayDTO(lstSSPTQ);
//			model.addAttribute("dataSearch", dataSearch);
//			model.addAttribute("resultSP", resultSP);
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
//		
//		return "admin/product/banHangTaiQuay";
//	}
	
	@PostMapping("/banHang/hoaDonChiTiet")
	public String addSanPhamChiTietVaoHDCT(ModelMap model, @ModelAttribute("resultSP") SPTaiQuayDTO dto) {
		Optional<SanPhamChiTiet> optSpct = sanPhamChiTietService.getSanPhamChiTietByMauSacSizeSanPhamId(dto.getSanPhamIdSPTQ(), dto.getMauSacId(), dto.getKichCoId());
		if(optSpct.isPresent()) {
			System.out.println("sl: "+dto.getSoLuong());
			System.out.println(optSpct.get().getId());
		}
		Optional<HoaDon> optHD = hoaDonRepository.findById(dto.getHoaDonId());
		if(optHD.isPresent()) {
			 model.addAttribute("hoaDon", optHD.get());
		}
		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
		return "admin/banHang/banHangTaiQuay/banHang";
	}
    
    @RequestMapping("banHang/{id}")
    public String banHang(@PathVariable("id") Long id, Model model,  @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);
        int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		SPTaiQuayDTO resultSP = new SPTaiQuayDTO();
		resultSP.setHoaDonId(id);
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
        return "admin/banHang/banHangTaiQuay/banHang";
    }

    @RequestMapping("banHang/ThemSanPham")
    public String themSanPham(Model model) {
        List<SanPham> sanPham = sanPhamRepository.findAll();
        model.addAttribute("sanPham", sanPham);

        return "admin/banHang/banHangTaiQuay/modal";
    }

    
//    @RequestMapping("/{sanPhamId}/mausac-kichco")
//    public String getMauSacAndKichCo(@PathVariable Long sanPhamId, Model model) {
//        String kichCo = banHangService.getKichCo(sanPhamId);
//        String mauSac = banHangService.getMauSac(sanPhamId);
//        BigDecimal donGia = banHangRepository.getDonGia(sanPhamId);
//        System.out.println(kichCo);
//        System.out.println(mauSac);
//        System.out.println(donGia);
//        String[] kc = kichCo.split(",");
//        String[] ms = mauSac.split(",");
//        model.addAttribute("kichCo", kc);
//        model.addAttribute("mauSac", ms);
//        model.addAttribute("gia", donGia);
//        return "admin/banHang/banHangTaiQuay/modalChiTietSanPham";
//    }

//    @RequestMapping("/timIdSanPhamChiTiet/{tenMauSac}/{tenKichCo}")
//    public ResponseEntity<Long> timIdSanPhamChiTiet(@PathVariable("tenMauSac") String tenMauSac, @PathVariable("tenKichCo") String tenKichCo, @RequestParam("soLuong") int soLuong, HttpSession session) {
//        Long sanPhamChiTietId = banHangService.findIdByTenKichCoAndTenMauSac(tenKichCo, tenMauSac);
//
//        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
//        sanPhamChiTiet.setId(sanPhamChiTietId);
//
//        BigDecimal donGia = BigDecimal.valueOf(0); // Thay đổi giá trị này bằng giá trị thực tế của đơn giá
//
//        HoaDonChiTiet hdct = new HoaDonChiTiet();
//        hdct.setSanPhamChiTiet(sanPhamChiTiet);
//        hdct.setDonGia(donGia);
//
//        BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
//        hdct.setTongTien(thanhTien);
//
//        hoaDonChiTietRepository.save(hdct);
//
//        return ResponseEntity.ok(sanPhamChiTietId);
//    }

//    @RequestMapping("/timIdSanPhamChiTiet/{tenMauSac}/{tenKichCo}")
//    public ResponseEntity<Long> timIdSanPhamChiTiet(@PathVariable("tenMauSac") String tenMauSac, @PathVariable("tenKichCo") String tenKichCo, HttpSession session) {
//        Long sanPhamChiTietId = banHangService.findIdByTenKichCoAndTenMauSac(tenKichCo, tenMauSac);
//        return ResponseEntity.ok(sanPhamChiTietId);
//    }
//    @PostMapping("/themHoaDonChiTiet")
//    public ResponseEntity<Long> themHoaDonChiTiet(@RequestParam("tenMauSac") String tenMauSac,
//                                                  @RequestParam("tenKichCo") String tenKichCo,
//                                                  @RequestParam("soLuong") int soLuong,
//                                                  @RequestParam("thanhTien") BigDecimal thanhTien) {
//        // Lấy ID của sản phẩm chi tiết dựa trên tên màu sắc và tên kích cỡ
//        Long sanPhamChiTietId = banHangService.findIdByTenKichCoAndTenMauSac(tenKichCo, tenMauSac);
//        System.out.println(sanPhamChiTietId);
//
//        // Tạo đối tượng HoaDonChiTiet và gán các giá trị
//        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
//        sanPhamChiTiet.setId(sanPhamChiTietId);
//
//        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
//        hoaDonChiTiet.setSoLuong(soLuong);
//        hoaDonChiTiet.setDonGia(thanhTien.divide(BigDecimal.valueOf(soLuong))); // Tính đơn giá từ thành tiền
//
//        // Lưu hóa đơn chi tiết vào cơ sở dữ liệu
//        HoaDonChiTiet savedHoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);
//
//        return ResponseEntity.ok(savedHoaDonChiTiet.getId());
//    }
}
