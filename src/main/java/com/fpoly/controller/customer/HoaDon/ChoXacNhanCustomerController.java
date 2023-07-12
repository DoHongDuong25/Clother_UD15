package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ChoXacNhanCustomerController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @RequestMapping("customer/DonHang/ChoXacNhan")
    public String choXacNhan(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "3") int size,
                             Model model){
        PageRequest pageable = PageRequest.of(page -1, size);
        Page<HoaDon> choXacNhan = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(1, pageable);

        model.addAttribute("choXacNhanCustomer", choXacNhan.getContent());
        model.addAttribute("pageChoXacNhan", choXacNhan.getTotalPages());

        return "customer/HoaDon/DanhSach/choXacNhanCustomer";
    }

        @RequestMapping("/customer/don-hang/huy-don-hang")
    public String huyDonHang(@RequestParam(name = "maDonHang", required = false) String maDonHang,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "3") int size,
                             Model model) {
        if (maDonHang != null) {
            hoaDonService.capNhatTrangThaiHuyDon(maDonHang);
            choXacNhan(page, size, model);
        }
        return "customer/HoaDon/DanhSach/choXacNhanCustomer";
    }

    @RequestMapping("customer/updateHuyDon/{id}")
    public ResponseEntity<String> updateHuyDon(@PathVariable("id") Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setId(5L);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("hduong"); // Cập nhật người cập nhật (thay "hduong" bằng giá trị tương ứng)
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNguoiDung(hoaDon.getNguoiDung().getId());
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
//            System.out.println(gd);
            giaoDichRepository.save(gd);
            String message = "Hủy đơn thành công";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping("customer/DonHang/ChiTietHoaDon/ChoXacNhan/hoa-don-id={id}")
    public String CTChoGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("hoaDon", hoaDon);
        return "customer/HoaDon/ChiTietHoaDon/CTChoXacNhanCustomer";
    }

}
