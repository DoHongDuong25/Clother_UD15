package com.fpoly.controller.admin.voucher;

import com.fpoly.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class VoucherController {
    private final KhuyenMaiService khuyenMaiService;
    @GetMapping("/voucher")
    public String getVoucher(Model model,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "keyword", required = false) String keyword){
        model.addAttribute("vouchers",khuyenMaiService.getListKhuyenMai(page, size, keyword));
        return "admin/voucher/index";
    }
}
