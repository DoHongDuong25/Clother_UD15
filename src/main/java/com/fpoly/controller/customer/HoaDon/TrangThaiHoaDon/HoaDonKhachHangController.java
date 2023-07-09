package com.fpoly.controller.customer.HoaDon.TrangThaiHoaDon;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HoaDonKhachHangController {
    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @RequestMapping("Customer/ChoGiaoHang/khach-hang-id={id}")
    public String ChoGiaoHang(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "3") int size,
                              @PathVariable("id") Long khachHangid) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> choGiaoHang = hoaDonRepoditory2.findHoaDonByTrangThaiAndKhachHangId(2, khachHangid, pageable);
        model.addAttribute("choGiaoHang", choGiaoHang);
        model.addAttribute("pageChoGiaoHang", choGiaoHang.getTotalPages());
        return "customer/HoaDon/TrangThaiHoaDon/choGiaoHang";
    }
}
