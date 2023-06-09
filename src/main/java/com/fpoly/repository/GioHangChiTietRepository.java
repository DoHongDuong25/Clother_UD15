package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.GioHangChiTiet;


@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {

}
