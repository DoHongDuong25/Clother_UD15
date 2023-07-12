package com.fpoly.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;

@Repository
@Primary
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Long>, SanPhamChiTietSearchRepository {
	@Query(value = "SELECT * FROM san_pham_chi_tiet c WHERE c.da_xoa = false", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietExist();
	
	@Query(value = "SELECT * FROM san_pham_chi_tiet s WHERE s.da_xoa = false AND s.san_pham_id = :id ORDER BY s.mau_sac_id", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT s.* FROM san_pham_chi_tiet s WHERE s.san_pham_id = :id AND s.da_xoa = false", nativeQuery = true)
	List<SanPhamChiTiet> getLstSanPhamChiTietAddImg(@Param("id") Long id);
	
//	@Query(value = "SELECT s.id AS 'id', s.so_luong AS 'soLuong', s.co_hien_thi AS 'coHienThi', m.ten_mau_sac AS 'tenMauSac', k.ten_kich_co AS 'tenKichCo' "
//			+ "FROM san_pham_chi_tiet s RIGHT JOIN mau_sac m ON s.mau_sac_id = m.id RIGHT JOIN kich_co k ON s.kich_co_id = k.id  "
//			+ "WHERE s.da_xoa = false AND m.da_xoa = false AND k.da_xoa = false AND s.san_pham_id = :id ORDER BY s.mau_sac_id DESC", nativeQuery = true)
////	@Query("SELECT new SanPhamChiTietMauSacKichCo(s.id, s.soLuong, s.coHienThi, m.tenMauSac, k.tenKichCo) FROM sanPhamChiTiet s, mauSac m, kichCo k ...")
//	List<SanPhamChiTietMauSacKichCo> getLstProductDetailsWithColorSize(@Param("id") Long id);
	@Query(value = "SELECT s1.* FROM san_pham_chi_tiet s1 where s1.da_xoa = false and s1.co_hien_thi = true order by s1.id", nativeQuery = true)
	Page<SanPhamChiTiet> getAllProductDetailIsShowTrue(Pageable pageable);
	
	@Query(value = "SELECT DISTINCT s.mau_sac_id FROM san_pham_chi_tiet s WHERE s.san_pham_id = :id", nativeQuery = true)
	List<Long> getLstMauSacBySanPhamId(@Param("id") Long sanPhamId);
	
	@Query(value = "SELECT * FROM san_pham_chi_tiet s WHERE s.san_pham_id = :sanPhamId AND s.mau_sac_id = :mauSacId AND s.kich_co_id = :kichCoId", nativeQuery = true)
	Optional<SanPhamChiTiet> getSanPhamChiTietByMauSacSizeSanPhamId(@Param("sanPhamId") Long sanPhamId,@Param("mauSacId") Long mauSacId,@Param("kichCoId") Long kichCoId);
}
