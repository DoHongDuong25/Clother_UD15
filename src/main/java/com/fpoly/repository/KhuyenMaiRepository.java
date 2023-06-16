package com.fpoly.repository;

import com.fpoly.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,Long> {
    Page<KhuyenMai> findAllByTenKhuyenMaiLike(String keyword);
}
