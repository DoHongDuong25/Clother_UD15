package com.fpoly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HoaDonChiTiet;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Long> {
    List<HoaDonChiTiet> findByHoaDonId(Long hoaDonId);
    
    @Query(value = "SELECT count(*) FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Integer countByHoaDonId(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id")Long id, Pageable pageable);
}
