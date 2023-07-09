package com.fpoly.controller.admin.HoaDon.TrangThaiHoaDon;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.GiaoDichService;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaHuyController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepository2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    GiaoDichService giaoDichService;

    @Autowired
    HoaDonService hoaDonService;

    @RequestMapping("admin/DonHang/DaHuyHang")
    public String getHoaDonDangGiao(Model model,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page -1, size);
        Page<HoaDon> DaHuy = hoaDonRepository2.findByTrangThaiHoaDonListTrangThai(5, pageable);

        model.addAttribute("DaHuy", DaHuy.getContent());
        model.addAttribute("pageDaHuy", DaHuy.getTotalPages());
        return "admin/hoadon/TrangThaiHoaDon/DaHuy";
    }
}
