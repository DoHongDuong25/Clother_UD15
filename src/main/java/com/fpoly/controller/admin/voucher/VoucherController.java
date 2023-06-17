package com.fpoly.controller.admin.voucher;

import com.fpoly.dto.KhuyenMaiDTO;
import com.fpoly.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class VoucherController {
    public static final String REDIRECT_GET_VOUCHER = "redirect:/admin/voucher";
    private final KhuyenMaiService khuyenMaiService;
    @GetMapping("/voucher")
    public String getVoucher(Model model,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "keyword", required = false) String keyword){
        model.addAttribute("vouchers",khuyenMaiService.getListKhuyenMai(page, size, keyword));
        return "admin/voucher/index";
    }
    @PostMapping("/voucher")
    public String createVoucher(@ModelAttribute @Valid KhuyenMaiDTO dto){
        khuyenMaiService.createVoucher(dto);
        return REDIRECT_GET_VOUCHER;
    }
    @PostMapping("/voucher/{id}")
    public String editVoucher(@PathVariable Long id, @ModelAttribute @Valid KhuyenMaiDTO dto){
        khuyenMaiService.editVoucher(id, dto);
        return REDIRECT_GET_VOUCHER;
    }
    @PostMapping("/voucher/{id}")
    public String deleteVoucher(@PathVariable Long id){
        khuyenMaiService.deleteVoucher(id);
        return REDIRECT_GET_VOUCHER;
    }
    @PostMapping("/voucher/toggle/{id}")
    public String disableVoucher(@PathVariable Long id){
        khuyenMaiService.toggleDisableVoucher(id);
        return REDIRECT_GET_VOUCHER;
    }
}
