package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.TrangThaiHoaDon;

@Repository
public interface TrangThaiHoaDonRepository extends JpaRepository<TrangThaiHoaDon,Long> {

}
