package com.fpoly.repository;

import com.fpoly.entity.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long> {
    // Các phương thức truy vấn và xử lý dữ liệu khác
}