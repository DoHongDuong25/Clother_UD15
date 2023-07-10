package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
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
public class DaHuyCustomerController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @RequestMapping("customer/DonHang/DaHuy")
    public String daHuyCustomer(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "3") int size,
                                Model model){
        PageRequest pageable = PageRequest.of(page -1, size);
        Page<HoaDon> DaHuy = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(5, pageable);

        model.addAttribute("DaHuyCustomer", DaHuy.getContent());
        model.addAttribute("pageDaHuy", DaHuy.getTotalPages());
        return "customer/HoaDon/DanhSach/daHuyCustomer";
    }

    @RequestMapping("customer/DonHang/ChiTietHoaDon/DaHuy/hoa-don-id={id}")
    public String DaHuy(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("hoaDon", hoaDon);
        return "customer/HoaDon/ChiTietHoaDon/CTDaHuyCustomer";
    }
}
