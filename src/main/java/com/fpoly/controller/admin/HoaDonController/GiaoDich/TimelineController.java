package com.fpoly.controller.admin.HoaDonController.GiaoDich;

import com.fpoly.entity.GiaoDich;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.service.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TimelineController {
    @Autowired
    GiaoDichService giaoDichService;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    //TIMELINE CHỜ XÁC NHẬN
    @RequestMapping("/giaodich/hoadon/ChoXacNhan/{id}")
    public String timeLineChoXacNhan(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        return "admin/hoadon/GiaoDich/TimelineChoXacNhan";
    }

    //TIMELINE CHỜ GIAO HÀNG
    @RequestMapping("/giaodich/hoadon/ChoGiaoHang/{id}")
    public String timeLineChoGiaoHang(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        System.out.println(timeLineChoGiaoHang);
        return "admin/hoadon/GiaoDich/TimelineChoGiaoHang";
    }

    //TIMELINE ĐANG GIAO HÀNG
    @RequestMapping("/giaodich/hoadon/DangGiaoHang/{id}")
    public String timeLineDangGiaoHang(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, hoaDonId);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        System.out.println(timeLineChoGiaoHang);
        return "admin/hoadon/GiaoDich/TimelineDangGiaoHang";
    }

    //TIMELINE ĐÃ GIAO HÀNG
    @RequestMapping("/giaodich/hoadon/DaGiaoHang/{id}")
    public String timeLineDaGiaoHang(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, hoaDonId);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, hoaDonId);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        System.out.println(timeLineChoGiaoHang);
        return "admin/hoadon/GiaoDich/TimelineDaGiaoHang";
    }

    //TIMELINE HỦY ĐƠN HÀNG
    @RequestMapping("/giaodich/hoadon/HuyDonHang/{id}")
    public String timeLineHuyDonHang(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        return "admin/hoadon/GiaoDich/TimelineDaHuyDon";
    }

    //TIMELINE TẤT CẢ ĐƠN
    @RequestMapping("/giaodich/hoadon/TatCaDon/{id}")
    public String timeLineTatCaDon(@PathVariable("id") Long hoaDonId, Model model) {
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, hoaDonId);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, hoaDonId);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, hoaDonId);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, hoaDonId);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, hoaDonId);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        System.out.println(timeLineChoGiaoHang);
        return "admin/hoadon/GiaoDich/TimeLineTatCaDon";
    }
}
