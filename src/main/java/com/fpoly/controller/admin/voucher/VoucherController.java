package com.fpoly.controller.admin.voucher;

import com.fpoly.dto.KhuyenMaiDTO;
import com.fpoly.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                             @RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "status", defaultValue = "ALL") String status,
                             @RequestParam(value = "discountStart", defaultValue = "0") String startStr,
                             @RequestParam(value = "discountEnd", defaultValue = "100") String endStr){
        Integer start = Integer.parseInt(startStr);
        Integer end = Integer.parseInt(endStr);
        Page<KhuyenMaiDTO> list = khuyenMaiService.getListKhuyenMai(page, size, keyword, status, start, end);
        model.addAttribute("vouchers", list);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("size", size);
        model.addAttribute("dateFrom", new Date());
        model.addAttribute("dateTo", new Date());
        int totalPages = list.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return ADMIN_VOUCHER_INDEX;
    }
    @GetMapping("/voucher/create")
    public String createVoucherForm(Model model){
        model.addAttribute("voucher", new KhuyenMaiDTO());
        return ADMIN_VOUCHER_EDIT;
    }
    @PostMapping("/voucher/create")
    public String handleCreate(@ModelAttribute @Valid KhuyenMaiDTO dto){
        khuyenMaiService.createVoucher(dto);
        return REDIRECT_GET_VOUCHER;
    }
    @GetMapping("/voucher/edit/{id}")
    public String editVoucher(Model model,
                             @PathVariable Long id){
        model.addAttribute("voucher",khuyenMaiService.getVoucher(id));
        return ADMIN_VOUCHER_EDIT;
    }
    @PostMapping("/voucher/edit/{id}")
    public String handleEdit(@PathVariable Long id, @ModelAttribute @Valid KhuyenMaiDTO dto){
        khuyenMaiService.editVoucher(id, dto);
        return REDIRECT_GET_VOUCHER;
    }
    @GetMapping("/voucher/delete/{id}")
    public String deleteVoucher(@PathVariable Long id){
        khuyenMaiService.deleteVoucher(id);
        return REDIRECT_GET_VOUCHER;
    }
    @GetMapping("/voucher/toggle/{id}")
    public String disableVoucher(@PathVariable Long id){
        khuyenMaiService.toggleDisableVoucher(id);
        return REDIRECT_GET_VOUCHER;
    }
}
