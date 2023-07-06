package com.fpoly.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.DiaChi;


@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {
	/*@Query(value="SELECT d FROM DiaChi d WHERE d.khachHang.id=?1")
	DiaChi findByKhachHangId(Long id);*/

	@Query(value="SELECT count(d) FROM DiaChi d WHERE d.khachHang.id=?1")
	int countByMaKhachHang(Long id);

	@Query(value="SELECT d FROM DiaChi d WHERE d.khachHang.id=:id")
	Page<DiaChi> findAllByMaKhachHang(@Param("id")Long id, Pageable pageale);
}
