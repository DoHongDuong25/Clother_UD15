package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HoaDonChiTiet;

@Repository
public interface HoaDonChiTietChatLieuRepository extends JpaRepository<HoaDonChiTiet,Long> {

}
