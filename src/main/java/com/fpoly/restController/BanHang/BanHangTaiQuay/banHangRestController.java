package com.fpoly.restController.BanHang.BanHangTaiQuay;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.HoaDonChiTietRepository;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class banHangRestController {

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;


    @RequestMapping("/banHang/laySoLuongSanPhamChiTiet")
    public Map<String, Object> laySoLuongSanPhamChiTiet(@RequestParam("tenKichCo") String tenKichCo, @RequestParam("sanPhamId") Long sanPhamId) {
        Map<String, Object> response = new HashMap<>();

        Integer soLuongSanPhamChiTiet = sanPhamChiTietRepository.laySoLuongSanPhamChiTiet(tenKichCo, sanPhamId);

        response.put("soLuongSanPhamChiTiet", soLuongSanPhamChiTiet);
        return response;
    }

    //XÓA SẢN PHẨM TRONG ĐƠN HÀNG
    @RequestMapping("/update-XoaSP/{id}")
    public ResponseEntity<String> updateXoaSP(@PathVariable("id") Long id) {
        Optional<HoaDonChiTiet> optionalHoaDon = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDon.get();
            hoaDonCT.setDaXoa(true);
            hoaDonChiTietRepository.save(hoaDonCT);

            HoaDon hoaDon = hoaDonCT.getHoaDon();
            hoaDon.getHoaDonChiTiets().remove(hoaDonCT);

            BigDecimal tongTien = hoaDon.getHoaDonChiTiets()
                    .stream()
                    .filter(hdct -> !hdct.isDaXoa()) // Lọc chỉ các hóa đơn chi tiết chưa bị xóa
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            //CẬP NHẬT SỐ LƯỢNG SẢN PHẨM CHI TIẾT
            SanPhamChiTiet sanPhamChiTiet = optionalHoaDon.get().getSanPhamChiTiet();
            Integer soLuongSPCTBanDau = optionalHoaDon.get().getSanPhamChiTiet().getSoLuong();
            Integer soLuongNhapVao = optionalHoaDon.get().getSoLuong();
            Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongNhapVao;

            sanPhamChiTiet.setSoLuong(soLuongcapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);

            String message = "Xác nhận thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE SỐ LƯỢNG CỦA HÓA ĐƠN CHI TIẾT
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

            BigDecimal tongTienHoaDon = hoaDon.getHoaDonChiTiets()
                    .stream()
                    .filter(hdct -> !hdct.isDaXoa())
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

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

    //HỦY ĐƠN HÀNG VÀ CẬP NHẬT LẠI SỐ LƯỢNG SẢN PHẨM
    @RequestMapping("/HuyDon/{id}")
    public ResponseEntity<String> huyDon(@PathVariable("id") Long id) {
        Optional<HoaDon> otpHoaDOn = hoaDonRepository.findById(id);
        if (otpHoaDOn.isPresent()) {
            HoaDon hoaDon = otpHoaDOn.get();
            hoaDon.setDaXoa(true);
            hoaDonRepository.save(hoaDon);

            // Cập nhật số lượng sản phẩm chi tiết và tổng tiền cho toàn bộ hóa đơn chi tiết
            for (HoaDonChiTiet hoaDonCT : hoaDon.getHoaDonChiTiets()) {
                hoaDonCT.setDaXoa(true);
                hoaDonChiTietRepository.save(hoaDonCT);

                SanPhamChiTiet sanPhamChiTiet = hoaDonCT.getSanPhamChiTiet();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongNhapVao = hoaDonCT.getSoLuong();
                Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongNhapVao;

                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }

            BigDecimal tongTien = hoaDon.getHoaDonChiTiets()
                    .stream()
                    .filter(hdct -> !hdct.isDaXoa()) // Lọc chỉ các hóa đơn chi tiết chưa bị xóa
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            String mss = "Hủy thành công";
            return ResponseEntity.ok(mss);
        } else {
            String erro = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }
}
