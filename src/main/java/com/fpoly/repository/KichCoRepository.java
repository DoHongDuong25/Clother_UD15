package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KichCo;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Long> {
	@Query(value = "SELECT * FROM kich_co c WHERE c.da_xoa = false", nativeQuery = true)
	List<KichCo> selectAllKichCoExist();

	@Query(value = "SELECT  c.* FROM kich_co c join san_pham_chi_tiet s1 on s1.kich_co_id = c.id WHERE c.da_xoa = false and s1.san_pham_id = :sanPhamId group by c.id", nativeQuery = true)
	List<KichCo> selectAllKichCoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}
