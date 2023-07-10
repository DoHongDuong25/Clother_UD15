package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class DangGiaoCustomerController {
    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @RequestMapping("customer/DonHang/DangGiaoHang")
    public String DangGiaoCustomer(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> dangtGiao = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(3, pageable);

        model.addAttribute("dangtGiaoCustomer", dangtGiao.getContent());
        model.addAttribute("pageDangGiao", dangtGiao.getTotalPages());
        return "customer/HoaDon/DanhSach/dangGiaoCustomer";
    }

    @RequestMapping("customer/DonHang/ChiTietHoaDon/DangGiaoHang/hoa-don-id={id}")
    public String CTDangGiaoCustomer(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "customer/HoaDon/ChiTietHoaDon/CTDangGiaoCustomer";
    }

    @RequestMapping("customer/updateGiaoHangThanhCong/{id}")
    public ResponseEntity<String> updateGiaoHangThanhCong(@PathVariable("id") Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setId(4L);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("hduong");
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNguoiDung(hoaDon.getNguoiDung().getId());
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);
            String message = "Đã xác nhận đơn hàng là đang giao";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("customer/updateThanhCongAll")
    public ResponseEntity<String> updateThanhCongAll() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(3);

        if (!hoaDonList.isEmpty()) {
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setId(4L);
                hoaDon.setTrangThai(tt);
                hoaDon.setNgayCapNhat(new Date());
                hoaDon.setNguoiCapNhat("hduong");
                hoaDonRepository.save(hoaDon);
                GiaoDich gd = new GiaoDich();
                gd.setHoaDon(hoaDon);
                gd.setNguoiDung(hoaDon.getNguoiDung().getId());
                gd.setNgayCapNhat(new Date());
                gd.setNgayTao(new Date());
                gd.setNguoiCapNhat("ABC");
                gd.setNguoiTao("ABC");
                gd.setTrangThai(tt);
                giaoDichRepository.save(gd);
            }

            String message = "Xác nhận tất cả thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chưa xác nhận";
            return ResponseEntity.notFound().build();
        }
    }
}
