package com.fpoly.repository;

import com.fpoly.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,Long> {
    @Query("SELECT p FROM KhuyenMai p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR p.tenKhuyenMai LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:status = 'ALL' OR (:status = 'ON' AND p.trangThai = true) OR (:status = 'off' AND p.trangThai = false)) " +
            "AND (:date = 'ALL' OR (:date = 'ON' AND p.ngayBatDau <= CURRENT_DATE AND p.ngayKetThuc >= CURRENT_DATE) or (:date = 'OFF' AND p.ngayKetThuc < CURRENT_DATE) OR (:date = 'PENDING' AND p.ngayBatDau > CURRENT_DATE))" +
            "AND p.xoa = false")
    Page<KhuyenMai> findVoucher(String keyword, String status, String date, Pageable pageable);

}
