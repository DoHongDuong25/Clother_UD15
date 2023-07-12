package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import com.fpoly.service.TrangThaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @RequestMapping("admin/BanHangTaiQuay")
    public String BanHangTaQuay(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> danhSachBanHang = hoaDonRepoditory2.finHDByLoaiHD(1, pageable);
        model.addAttribute("danhSachBanHang", danhSachBanHang.getContent());
        model.addAttribute("pageDanhSachBanHang", danhSachBanHang.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
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
