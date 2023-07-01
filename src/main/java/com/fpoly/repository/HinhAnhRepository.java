package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HinhAnh;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
	@Query(value ="SELECT h.* FROM `hinh_anh` h LEFT JOIN mau_sac m ON h.mau_sac_id = m.id LEFT JOIN san_pham s ON h.san_pham_id = s.id WHERE h.san_pham_id = :sanPhamId ORDER BY h.id DESC", nativeQuery = true)
	List<HinhAnh> getLstHinhAnhByMauSacIdAndSanPhamId(@Param("sanPhamId") Long sanPhamId);
	
	@Query(value ="SELECT DISTINCT(h.mau_sac_id) FROM `hinh_anh` h LEFT JOIN mau_sac m ON h.mau_sac_id = m.id LEFT JOIN san_pham s ON h.san_pham_id = s.id WHERE h.san_pham_id = 1 ORDER BY h.id DESC", nativeQuery = true)
	List<Long> getDistinctMauSacInHinhAnhBySanPhamId(@Param("sanPhamId") Long sanPhamId);
}
