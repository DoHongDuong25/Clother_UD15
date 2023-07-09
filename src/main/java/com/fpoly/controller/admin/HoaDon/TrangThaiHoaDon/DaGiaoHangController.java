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
public class DaGiaoHangController {
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

    @RequestMapping("admin/DonHang/DaGiaoHang")
    public String getHoaDonDaGiao(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page -1, size);
        Page<HoaDon> DaGiao = hoaDonRepository2.findByTrangThaiHoaDonListTrangThai(4, pageable);

        model.addAttribute("DaGiao", DaGiao.getContent());
        model.addAttribute("pageDaGiao", DaGiao.getTotalPages());
        return "admin/hoadon/TrangThaiHoaDon/DaGiao";
    }
}
