package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.SanPhamChiTiet;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Long> {

}
