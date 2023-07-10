package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;


public interface SanPhamChiTietService {

	List<SanPhamChiTiet> getLstSanPhamChiTietExist();

	Optional<SanPhamChiTiet> findById(Long id);

	List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(Long id);

	Page<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable);

	void delete(SanPhamChiTiet entity);

	<S extends SanPhamChiTiet> S save(S entity);

	List<SanPhamChiTiet> getLstSanPhamChiTietAddImg(Long id);

	List<Long> getLstMauSacBySanPhamId(Long sanPhamId);

//	List<SanPhamChiTietMauSacKichCo> getLstProductDetailsWithColorSize(Long id);

}
