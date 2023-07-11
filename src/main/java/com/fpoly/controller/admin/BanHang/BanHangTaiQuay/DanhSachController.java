package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import com.fpoly.service.TrangThaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DanhSachController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    TrangThaiService trangThaiService;

    @RequestMapping("admin/BanHangTaiQuay")
    public String BanHangTaQuay(Model model) {
        List<HoaDon> danhSachBanHang = hoaDonRepository.finHDByLoaiHD(1);
        model.addAttribute("danhSachBanHang", danhSachBanHang);
        return "admin/banHang/banHangTaiQuay/DanhSach";
    }

    @PostMapping("/TaoHoaDon")
    public String taoHoaDon(HoaDon hoaDon, RedirectAttributes redirectAttributes) {
        Integer maxId = hoaDonService.getMaxId();
        int id;
        String ma;

        if (maxId != null) {
            id = maxId + 1;
        } else {
            id = 1;
        }

        DecimalFormat df = new DecimalFormat("00");
        String formattedId = df.format(id);

        ma = "HD" + formattedId;

        TrangThai trangThai = trangThaiService.getTrangThaiById(6L);
        hoaDon.setMaDon(ma);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNguoiTao("hduong");
        hoaDon.setLoaiHoaDon(1);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setTongTienHoaDon(BigDecimal.valueOf(0));
        hoaDon.setTongTienDonHang(BigDecimal.valueOf(0));
        hoaDonRepository.save(hoaDon);

        // Thêm mã hóa đơn vào redirectAttributes để truyền cho trang chuyển hướng
        redirectAttributes.addAttribute("id", hoaDon.getId());

        return "redirect:/banHang/{id}";
    }

}
