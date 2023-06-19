package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;


public interface SanPhamChiTietService {

	List<SanPhamChiTiet> getLstSanPhamChiTietExist();

	Optional<SanPhamChiTiet> findById(Long id);

	List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(Long id);

	List<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data);

	void delete(SanPhamChiTiet entity);

	<S extends SanPhamChiTiet> S save(S entity);

}
