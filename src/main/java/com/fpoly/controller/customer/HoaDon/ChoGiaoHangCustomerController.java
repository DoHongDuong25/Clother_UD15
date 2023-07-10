package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChoGiaoHangCustomerController {
    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    //CHỜ GIAO HÀNG
    @RequestMapping("customer/DonHang/ChoGiaoHang")
    public String choGiaoHang(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "3") int size,
                              Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> ChoGiaoHang = hoaDonRepoditory2.findHoaDonbyId(2, pageable);
        model.addAttribute("customerChoGiaoHang", ChoGiaoHang.getContent());
        model.addAttribute("pageChoGiaoHang", ChoGiaoHang.getTotalPages());
        return "customer/HoaDon/DanhSach/choGiaoHangCustomer";
    }

    @RequestMapping("customer/DonHang/ChiTietHoaDon/ChoGiaoHang/hoa-don-id={id}")
    public String CTChoGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "customer/HoaDon/ChiTietHoaDon/CTChoGiaoHangCustomer";
    }
}
