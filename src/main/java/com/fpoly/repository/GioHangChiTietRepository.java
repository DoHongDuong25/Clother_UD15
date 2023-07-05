package com.fpoly.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.GioHangChiTiet;


@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {
	
	@Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.gioHang.id =:id")
	public List<GioHangChiTiet> findAllByGioHangId(@Param("id") Long id);

	@Query("SELECT SUM(ghct.thanhTien) FROM GioHangChiTiet ghct WHERE ghct.gioHang.id =:id")
	public int tongTien(@Param("id")Long id);
	
}
