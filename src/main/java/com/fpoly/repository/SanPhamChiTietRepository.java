package com.fpoly.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;

@Repository
@Primary
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Long>, SanPhamChiTietSearchRepository {
	@Query(value = "SELECT * FROM san_pham_chi_tiet c WHERE c.da_xoa = false", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietExist();
	
	@Query(value = "SELECT * FROM san_pham_chi_tiet c WHERE c.da_xoa = false AND c.san_pham_id = :id", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM san_pham_chi_tiet c WHERE c.da_xoa = false AND c.san_pham_id = :id", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(@Param("id") Long id);
}
