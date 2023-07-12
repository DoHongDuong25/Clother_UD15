package com.fpoly.restController;

import com.fpoly.service.banHangService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class banHangRestController {
    private final banHangService banHangService;

    public banHangRestController(banHangService banHangService) {
        this.banHangService = banHangService;
    }

//    @RequestMapping("/{sanPhamId}/mausac-kichco")
//    public ResponseEntity<List<String>> getMauSacAndKichCo(@PathVariable Long sanPhamId) {
//        try {
//            String mauSacAndKichCo = banHangService.getMauSacAndKichCo(sanPhamId);
//            List<String> attributes = Arrays.asList(mauSacAndKichCo.split(","));
//            return ResponseEntity.ok(attributes);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

}
