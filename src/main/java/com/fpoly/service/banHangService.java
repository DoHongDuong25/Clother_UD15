package com.fpoly.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface banHangService {
    String getMauSac(Long sanPhamId);

    String getKichCo(Long sanPhamId);

    Long findIdByTenKichCoAndTenMauSac(String tenKichCo, String tenMauSac);
}
