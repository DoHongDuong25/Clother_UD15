package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPham;


public interface SanPhamService {
	Optional<SanPham> findById(Long id);

	<S extends SanPham> S save(S entity);

}
