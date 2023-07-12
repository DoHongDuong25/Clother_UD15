package com.fpoly.repository;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository2 extends PagingAndSortingRepository<HoaDonChiTiet, Long> {
    Page<HoaDonChiTiet> findHDCTByHoaDonId(Long hoaDonId, Pageable pageable);
}
