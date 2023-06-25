package com.fpoly.controller.admin.HoaDonController.BanHangTaiQuay;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BanHangTaiQuayController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @RequestMapping("admin/BanHangTaiQuay")
    public String BanHangTaQuay(Model model){
        List<HoaDon> hoaDonLoai1 =  hoaDonRepository.finHDByLoaiHD(1);
        model.addAttribute("hoaDonLoai1", hoaDonLoai1);
        return "admin/hoadon/BanHangTaiQuay/BanHangTaiQuay";
    }

}
