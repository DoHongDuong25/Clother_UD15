package com.fpoly.service.impl;

import com.fpoly.repository.HoaDonRepository;
import com.fpoly.repository.banHangRepository;
import com.fpoly.service.banHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class banHangServiceIpml implements banHangService {
    private final banHangRepository banHangRepository;

    public banHangServiceIpml(banHangRepository banHangRepository) {
        this.banHangRepository = banHangRepository;
    }

    @Override
    public String getKichCo(Long sanPhamId) {
        return banHangRepository.getKichCo(sanPhamId);
    }

    @Override
    public String getMauSac(Long sanPhamId) {
        return banHangRepository.getMauSac(sanPhamId);
    }

    @Override
    public Long findIdByTenKichCoAndTenMauSac(String tenKichCo, String tenMauSac) {
        return banHangRepository.findIdByTenKichCoAndTenMauSac(tenKichCo, tenMauSac);
    }
}
