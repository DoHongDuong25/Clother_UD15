package com.fpoly.service.impl;

import com.fpoly.repository.HoaDonRepository;
import com.fpoly.repository.banHangRepository;
import com.fpoly.service.banHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class banHangServiceIpml implements banHangService {
    private final banHangRepository banHangRepository;

    public banHangServiceIpml(banHangRepository banHangRepository) {
        this.banHangRepository = banHangRepository;
    }

    @Override
    public String getMauSacAndKichCo(Long sanPhamId) {
        return banHangRepository.getMauSacAndKichCo(sanPhamId);
    }
}
