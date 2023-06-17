package com.fpoly.repository;

import com.fpoly.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,Long> {
    @Query("select p from KhuyenMai p WHERE (:keyword IS NULL OR p.tenKhuyenMai LIKE :keyword) and p.enable = true")
    Page<KhuyenMai> findVoucher(String keyword, Pageable pageable);

}
