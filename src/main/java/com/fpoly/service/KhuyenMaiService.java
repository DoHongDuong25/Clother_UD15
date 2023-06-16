package com.fpoly.service;

import com.fpoly.entity.KhuyenMai;
import org.springframework.data.domain.Page;

public interface KhuyenMaiService {
    Page<KhuyenMai> getListKhuyenMai(int page, int size, String keyword);
}
