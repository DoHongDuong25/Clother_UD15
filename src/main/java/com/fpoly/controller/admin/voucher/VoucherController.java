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
    private static final String REDIRECT_GET_VOUCHER = "redirect:/admin/voucher";
    private static final String ADMIN_VOUCHER_INDEX = "admin/voucher/index";
    private static final String ADMIN_VOUCHER_EDIT = "admin/voucher/edit";
    private final KhuyenMaiService khuyenMaiService;
    @GetMapping("/voucher")
    public String getVoucher(Model model,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "keyword", required = false) String keyword){
        model.addAttribute("vouchers",khuyenMaiService.getListKhuyenMai(page, size, keyword));
        return ADMIN_VOUCHER_INDEX;
    }
    @GetMapping("/voucher/edit/{id}")
    public String editVoucher(Model model,
                             @PathVariable Long id){
        model.addAttribute("vouchers",khuyenMaiService.getVoucher(id));
        return ADMIN_VOUCHER_EDIT;
    }
    @GetMapping("/voucher/create")
    public String createVoucherForm(Model model){
        model.addAttribute("voucher", new KhuyenMaiDTO());
        return ADMIN_VOUCHER_EDIT;
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
    @PostMapping("/voucher/delete/{id}")
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
