package com.fpoly.controller.admin.HoaDon.TrangThaiHoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.GiaoDichService;
import com.fpoly.service.HoaDonService;
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
public class DangGiaoHangController {
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

    @RequestMapping("admin/DonHang/DangGiaoHang")
    public String getHoaDonDangGiao(Model model,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> dangtGiao = hoaDonRepository2.findByTrangThaiHoaDonListTrangThai(3, pageable);

        model.addAttribute("dangtGiao", dangtGiao.getContent());
        model.addAttribute("pageDangGiao", dangtGiao.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "admin/hoadon/TrangThaiHoaDon/DangGiao";
    }
}
