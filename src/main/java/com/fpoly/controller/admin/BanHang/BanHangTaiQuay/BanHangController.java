package com.fpoly.controller.admin.BanHang.BanHangTaiQuay;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BanHangController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @RequestMapping("banHang/{id}")
    public String banHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);
        return "admin/banHang/banHangTaiQuay/BanHang";
    }
}
