package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.DiaChi;


@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {
	@Query(value="SELECT d FROM DiaChi d WHERE d.khachHang.id=?1")
	DiaChi findByKhachHangId(Long id);
}
