package com.fpoly.service.impl;

import com.fpoly.dto.KhuyenMaiDTO;
import com.fpoly.entity.KhuyenMai;
import com.fpoly.repository.KhuyenMaiRepository;
import com.fpoly.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KhuyenMaiServiceImp implements KhuyenMaiService{
    private final KhuyenMaiRepository khuyenMaiRepository;
    @Override
    public Page<KhuyenMaiDTO> getListKhuyenMai(int page, int size, String keyword) {
        Sort sort = Sort.by("ngayBatDau").descending();
        PageRequest pageRequest = PageRequest.of(page-1, size, sort);
        Page<KhuyenMai> entities = khuyenMaiRepository.findVoucher(keyword, pageRequest);
        return entities.map(this::toDto);
    }

    private KhuyenMaiDTO toDto(KhuyenMai e) {
        if (e == null) return null;
        return KhuyenMaiDTO.builder()
                .tenKhuyenMai(e.getTenKhuyenMai())
                .giaTriToiThieu(e.getGiaTriToiThieu())
                .phanTramGiam(e.getPhanTramGiam())
                .ngayBatDau(e.getNgayBatDau())
                .ngayKetThuc(e.getNgayKetThuc())
                .trangThai(e.isTrangThai())
                .build();
    }
    private KhuyenMai toEntity(KhuyenMaiDTO e) {
        if (e == null) return null;
        return KhuyenMai.builder()
                .tenKhuyenMai(e.getTenKhuyenMai())
                .giaTriToiThieu(e.getGiaTriToiThieu())
                .phanTramGiam(e.getPhanTramGiam())
                .ngayBatDau(e.getNgayBatDau())
                .ngayKetThuc(e.getNgayKetThuc())
                .build();
    }

    @Override
    public KhuyenMaiDTO createVoucher(KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai entity = toEntity(khuyenMaiDTO);
        return toDto(khuyenMaiRepository.save(entity));
    }

    @Override
    public KhuyenMaiDTO editVoucher(Long id, KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(id).orElseThrow(() -> new RuntimeException("NOTFOUND"));
        mapDto(khuyenMai, khuyenMaiDTO);
        khuyenMaiRepository.save(khuyenMai);
        return toDto(khuyenMai);
    }
    private void mapDto(KhuyenMai entity, KhuyenMaiDTO dto){
        if (dto == null) return;
        entity.setTenKhuyenMai(dto.getTenKhuyenMai());
        entity.setGiaTriToiThieu(dto.getGiaTriToiThieu());
        entity.setPhanTramGiam(dto.getPhanTramGiam());
        entity.setNgayBatDau(dto.getNgayBatDau());
        entity.setNgayKetThuc(dto.getNgayKetThuc());
        entity.setTrangThai(dto.isTrangThai());
    }

    @Override
    public void deleteVoucher(Long id) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(id).orElseThrow(() -> new RuntimeException("NOTFOUND"));
        khuyenMai.setEnable(false);
        khuyenMaiRepository.save(khuyenMai);
    }

    @Override
    public void toggleDisableVoucher(Long id) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(id).orElseThrow(() -> new RuntimeException("NOTFOUND"));
        khuyenMai.setTrangThai(!khuyenMai.isTrangThai());
        khuyenMaiRepository.save(khuyenMai);
    }

}
