package com.fpoly.controller.admin.NhanVien;

import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class NhanVienController {
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    //List
    @GetMapping("/admin/NguoiDung")
    public String getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            Model model
    ) {
        Page<NguoiDung> users = nguoiDungService.getAll(page, size);
        System.out.println(users);
        model.addAttribute("users", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        return "admin/NguoiDung/list/NguoiDung";
    }

    //Add
    @RequestMapping("/NguoiDung/themMoi")
    public String themNguoiDung(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "admin/NguoiDung/crud/ThemNguoiDung";
    }

    @PostMapping("/themMoi")
    public String addNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, Model model) {
        boolean isValid = true;
        //Chenk tên người dùng
        if (nguoiDung.getTenNguoiDung().isEmpty()) {
            model.addAttribute("msgName", "Không được để trống tên người dùng");
            isValid = false;
        }

        if (nguoiDungRepository.findByEmail(nguoiDung.getEmail()) != null) {
            model.addAttribute("msgEmail", "Email đã được sử dụng");
            isValid = false;
        }
        //Chenk email
        if (nguoiDung.getEmail().isEmpty()) {
            model.addAttribute("msgEmail", "Không được để trống email");
            isValid = false;
        }

        if (nguoiDungRepository.findByEmail(nguoiDung.getEmail()) != null) {
            model.addAttribute("msgEmail", "Email đã được sử dụng");
            isValid = false;
        }
        //Chenk số điện thoại
        if (nguoiDung.getSoDienThoai().isEmpty()) {
            model.addAttribute("msgsodienthoai", "Không được để trống số điện thoại");
            isValid = false;
        }
        if (nguoiDungRepository.findBysoDienThoai(nguoiDung.getSoDienThoai()) != null) {
            model.addAttribute("msgsodienthoai", "Trùng sdt");
            isValid = false;
        }
        if (isValid) {
            Integer maxId = nguoiDungRepository.getMaxId(); // Sử dụng kiểu dữ liệu Integer thay vì List<Object>
            int id;
            String ma;

            if (maxId != null) {
                id = maxId + 1;
                ma = "NV" + id;
            } else {
                id = 1;
                ma = "NV" + id;
            }
            // Lưu mã người dùng vào đối tượng nguoiDung
            nguoiDung.setMaNguoiDung(ma);
            nguoiDung.setDaXoa(false);
            nguoiDung.setNgayCapNhat(new Date());
            nguoiDungRepository.save(nguoiDung);
            return "redirect:/admin/NguoiDung";
        } else {
            return "admin/NguoiDung/crud/ThemNguoiDung";
        }
    }

    //Update
    @RequestMapping("/chinhSua/{id}")
    public String chinhSuaNguoiDung(@PathVariable("id") Long id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        model.addAttribute("nguoiDungEdit", nguoiDung);
        return "admin/NguoiDung/crud/ChinhSuaNguoiDung";
    }

    @PostMapping("/chinhSua/save")
    public String editNguoiDung(@ModelAttribute("nguoiDungEdit") NguoiDung nguoiDungEdit, Model model) {
        boolean isValid = true;

        // Kiểm tra rỗng tên người dùng
        if (nguoiDungEdit.getTenNguoiDung().isEmpty()) {
            model.addAttribute("msgName", "Không được để trống tên người dùng");
            isValid = false;
        }

        // Kiểm tra rỗng email
        if (nguoiDungEdit.getEmail().isEmpty()) {
            model.addAttribute("msgEmail", "Không được để trống email");
            isValid = false;
        }

        // Kiểm tra rỗng số điện thoại
        if (nguoiDungEdit.getSoDienThoai().isEmpty()) {
            model.addAttribute("msgSoDienThoai", "Không được để trống số điện thoại");
            isValid = false;
        }

        // Kiểm tra trùng email
        NguoiDung existingNguoiDungByEmail = nguoiDungRepository.findByEmail(nguoiDungEdit.getEmail());
        if (existingNguoiDungByEmail != null && !existingNguoiDungByEmail.getId().equals(nguoiDungEdit.getId())) {
            model.addAttribute("msgEmail", "Email đã được sử dụng");
            isValid = false;
        }

        // Kiểm tra trùng số điện thoại
        NguoiDung existingNguoiDungBySoDienThoai = nguoiDungRepository.findBysoDienThoai(nguoiDungEdit.getSoDienThoai());
        if (existingNguoiDungBySoDienThoai != null && !existingNguoiDungBySoDienThoai.getId().equals(nguoiDungEdit.getId())) {
            model.addAttribute("msgSoDienThoai", "Số điện thoại đã được sử dụng");
            isValid = false;
        }

        if (isValid) {
            NguoiDung existingNguoiDung = nguoiDungService.getNguoiDungById(nguoiDungEdit.getId());
            existingNguoiDung.setTenNguoiDung(nguoiDungEdit.getTenNguoiDung());
            existingNguoiDung.setEmail(nguoiDungEdit.getEmail());
            existingNguoiDung.setSoDienThoai(nguoiDungEdit.getSoDienThoai());
            nguoiDungService.saveNguoiDung(existingNguoiDung);
            return "redirect:/admin/NguoiDung";
        } else {
            // Nếu có lỗi, trả về view chỉnh sửa với các thông báo lỗi
            return "admin/NguoiDung/crud/ChinhSuaNguoiDung";
        }
    }

    //Delete
    @RequestMapping("xoa/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
        if (optionalNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optionalNguoiDung.get();
            nguoiDung.setDaXoa(true);
            nguoiDungRepository.save(nguoiDung);
        }
        model.addAttribute("listProducts", nguoiDungRepository.findAll());
        return "redirect:/admin/NguoiDung";
    }

    //Search
    @GetMapping("/admin/search")
    public String searchNguoiDung(@RequestParam("id") Long id, Model model) {
        Optional<NguoiDung> searchResult = nguoiDungRepository.findById(id);
        if (searchResult.isPresent()) {
            List<NguoiDung> resultList = new ArrayList<>();
            resultList.add(searchResult.get());
            model.addAttribute("listNguoiDungAll", resultList);
        } else {
            model.addAttribute("listNguoiDungAll", Collections.emptyList());
        }
        return "admin/NguoiDung/list/NguoiDung";
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam("userId") Long id, @RequestParam("status") int trangThai) {
        try {
            nguoiDungService.updateUserStatus(id, trangThai);
            return ResponseEntity.ok("Cập nhật trạng thái thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật trạng thái");
        }
    }
}
