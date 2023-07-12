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
import com.fpoly.entity.*;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BanHangController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepository2;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;
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


    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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

    @PostMapping("/banHang/getData")
    public String showSanPhamChiTiet(ModelMap model, @ModelAttribute("resultSP") SPTaiQuayDTO dto) {
        Optional<SanPhamChiTiet> optSpct = sanPhamChiTietService.getSanPhamChiTietByMauSacSizeSanPhamId(dto.getSanPhamIdSPTQ(), dto.getMauSacId(), dto.getKichCoId());
        if (optSpct.isPresent()) {
            Optional<HoaDon> optHD = hoaDonRepository.findById(dto.getHoaDonId());
            if (optHD.isPresent()) {
                HoaDon hoaDon = optHD.get();
                BigDecimal giaSP = optSpct.get().getSanPham().getGia();
                Integer soLuong = dto.getSoLuong();

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setSanPhamChiTiet(optSpct.get());
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setDonGia(giaSP);
                hoaDonChiTiet.setSoLuong(soLuong);
                BigDecimal thanhTien = giaSP.multiply(BigDecimal.valueOf(soLuong));
                hoaDonChiTiet.setTongTien(thanhTien);
                hoaDonChiTiet.setDaXoa(false);
                hoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);

                hoaDon.getHoaDonChiTiets().add(hoaDonChiTiet);
                hoaDonRepository.save(hoaDon);

                if (hoaDon.getHoaDonChiTiets().isEmpty()) {
                    hoaDon.setTongTienDonHang(thanhTien);
                    hoaDon.setTongTienHoaDon(thanhTien);
                } else {
                    BigDecimal tongTienDonHang = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.isDaXoa()).map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

                    hoaDon.setTongTienDonHang(tongTienDonHang);
                    hoaDon.setTongTienHoaDon(tongTienDonHang);
                }

                hoaDon.setTienShip(BigDecimal.valueOf(0));
                hoaDonRepository.save(hoaDon);
                model.addAttribute("hoaDon", hoaDon);

                return "redirect:/banHang/" + dto.getHoaDonId();
            }
        }
        model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
        return "admin/banHang/banHangTaiQuay/banHang";
    }

    @RequestMapping("banHang/{id}")
    public String banHang(@PathVariable("id") Long id, Model model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,

                          @RequestParam(defaultValue = "1") int pageHDCT, @RequestParam(defaultValue = "5") int sizeHDCT) {

        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);

        //HÓA ĐƠN CHI TIẾT VÀ PHÂN TRANG HÓA ĐƠN CHI TIẾT
        PageRequest pageableHDCT = PageRequest.of(pageHDCT - 1, sizeHDCT);
        Page<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCTByHoaDonId(id, pageableHDCT);
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet.getContent());
        model.addAttribute("pageHoaDonChiTiet", hoaDonChiTiet.getTotalPages());
        model.addAttribute("pageHDCT", pageHDCT);
        model.addAttribute("sizeHDCT", sizeHDCT);


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

    @RequestMapping("/update-XoaSP/{id}")
    public ResponseEntity<String> updateXoaSP(@PathVariable("id") Long id) {
        Optional<HoaDonChiTiet> optionalHoaDon = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDon.get();
            hoaDonCT.setDaXoa(true);
            hoaDonChiTietRepository.save(hoaDonCT);

            HoaDon hoaDon = hoaDonCT.getHoaDon();
            hoaDon.getHoaDonChiTiets().remove(hoaDonCT);

            BigDecimal tongTien = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.isDaXoa()) // Lọc chỉ các hóa đơn chi tiết chưa bị xóa
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            String message = "Xác nhận thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update-SoLuong/{id}")
    public ResponseEntity<String> updateSoLuong(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        Optional<HoaDonChiTiet> optionalHoaDonCT = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDonCT.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDonCT.get();
            hoaDonCT.setSoLuong(quantity);

            BigDecimal donGia = hoaDonCT.getDonGia();
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(quantity));
            hoaDonCT.setTongTien(thanhTien);

            hoaDonChiTietRepository.save(hoaDonCT);

            HoaDon hoaDon = hoaDonCT.getHoaDon();

            BigDecimal tongTienHoaDon = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.isDaXoa()).map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTienHoaDon);
            hoaDon.setTongTienHoaDon(tongTienHoaDon);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            String message = "Lưu thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chi tiết";
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/HuyDon/{id}")
    public ResponseEntity<String> huyDon(@PathVariable("id") Long id) {
        Optional<HoaDon> otpHoaDOn = hoaDonRepository.findById(id);
        if (otpHoaDOn.isPresent()) {
            HoaDon hoaDon = otpHoaDOn.get();
            hoaDon.setDaXoa(true);
            hoaDonRepository.save(hoaDon);
            String mss = "Hủy thành công";
            return ResponseEntity.ok(mss);
        } else {
            String erro = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }
}
