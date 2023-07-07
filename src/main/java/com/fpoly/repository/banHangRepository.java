package com.fpoly.repository;

import com.fpoly.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface banHangRepository extends JpaRepository<SanPham, Long> {
    @Query(value = "SELECT " +
            "(SELECT GROUP_CONCAT(DISTINCT ten_mau_sac) " +
            "FROM san_pham " +
            "JOIN san_pham_chi_tiet spct ON san_pham.id = spct.san_pham_id " +
            "JOIN kich_co kc ON spct.kich_co_id = kc.id " +
            "JOIN mau_sac ms ON spct.mau_sac_id = ms.id " +
            "WHERE san_pham_id = ?1) AS mausac, " +
            "(SELECT GROUP_CONCAT(DISTINCT ten_kich_co) " +
            "FROM san_pham " +
            "JOIN san_pham_chi_tiet spct ON san_pham.id = spct.san_pham_id " +
            "JOIN kich_co kc ON spct.kich_co_id = kc.id " +
            "JOIN mau_sac ms ON spct.mau_sac_id = ms.id " +
            "WHERE san_pham_id = ?1) AS kichco", nativeQuery = true)

    String getMauSacAndKichCo(Long sanPhamId);
}
