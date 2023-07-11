package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.*;
import com.fpoly.service.banHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class BanHangController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepository2;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    banHangService banHangService;

    @Autowired
    banHangRepository banHangRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @RequestMapping("banHang/{id}")
    public String banHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);
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
