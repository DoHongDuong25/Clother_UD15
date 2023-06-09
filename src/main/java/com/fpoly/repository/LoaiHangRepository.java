package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.LoaiHang;

@Repository
public interface LoaiHangRepository extends JpaRepository<LoaiHang,Long> {

}
