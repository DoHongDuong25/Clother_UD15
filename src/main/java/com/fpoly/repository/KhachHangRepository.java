package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {

}
