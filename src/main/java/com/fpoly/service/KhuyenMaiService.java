package com.fpoly.service;

import com.fpoly.dto.KhuyenMaiDTO;
import org.springframework.data.domain.Page;

public interface KhuyenMaiService {
    Page<KhuyenMaiDTO> getListKhuyenMai(int page, int size, String keyword);
    KhuyenMaiDTO createVoucher(KhuyenMaiDTO khuyenMaiDTO);
    KhuyenMaiDTO editVoucher(Long id, KhuyenMaiDTO khuyenMaiDTO);
    void deleteVoucher(Long id);
    void toggleDisableVoucher(Long id);
}
