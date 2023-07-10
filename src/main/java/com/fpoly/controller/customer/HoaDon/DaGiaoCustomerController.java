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
public class DaGiaoCustomerController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @RequestMapping("customer/DonHang/DaGiaoHang")
    public String DaGiaoCustomer(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> DaGiao = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(4, pageable);

        model.addAttribute("DaGiaoCustomer", DaGiao.getContent());
        model.addAttribute("pageDaGiao", DaGiao.getTotalPages());
        return "customer/HoaDon/DanhSach/daGiaoCustomer";
    }
    @RequestMapping("customer/DonHang/ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "customer/HoaDon/ChiTietHoaDon/CTDaoGiaoCustomer";
    }
}
