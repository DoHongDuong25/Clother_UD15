package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
	
	
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.trangThai=?1")
	Page<KhachHang> findAllByTrangThaiCoPhanTrang(int trangThai,Pageable pageable);
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.trangThai =:trangThai AND k.soDienThoai LIKE %:soDienThoai%")
	Page<KhachHang> findAllByTrangThaiVaSoDienThoaiCoPhanTrang(@Param("trangThai")int trangThai,@Param("soDienThoai") String soDienThoai, Pageable pageable);
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.trangThai=?1")
	List<KhachHang> findAllByTrangThaiKhongCoPhanTrang(int trangThai);
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.email=?1")
	KhachHang findByEmail(String  email);
	
	@Modifying
	@Query(value="UPDATE KhachHang k SET k.trangThai = 1 WHERE k.id=?1")
	void capNhatTrangThaiThanhHoatDongTheoMa(long id);
	
	
	@Modifying
	@Query(value="UPDATE KhachHang k SET k.trangThai = 0 WHERE k.id=?1")
	void capNhatTrangThaiThanhKhongHoatDongTheoMa(long id);
	
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.soDienThoai LIKE %:dienThoai%")
	Page<KhachHang> findAllBySoDienThoaiCoPhanTrang(@Param("dienThoai")String dienThoai , Pageable pageable);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE k.trangThai=?1")
	int countByTrangThai(Integer trangThai);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE k.soDienThoai LIKE %:soDienThoai%")
	int countBySoDienThoai(@Param("soDienThoai")String soDienThoai);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE  k.soDienThoai LIKE %:soDienThoai%  AND k.trangThai =:trangThai")
	int countBySoDienThoaiVaTrangThai(@Param("soDienThoai")String soDienThoai, @Param("trangThai")Integer trangThai);
}
