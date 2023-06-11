package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.SanPhamChiTietRepository;
import com.fpoly.service.SanPhamChiTietService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService{
	private final SanPhamChiTietRepository sanPhamChiTietRepository;

	@Override
	public List<SanPhamChiTiet> getLstSanPhamChiTietExist() {
//		String andStr = " AND c.co_hien_thi = ";
//		if(and.equalsIgnoreCase(andStr)) {
//			andStr.concat(and);
//		}
		return sanPhamChiTietRepository.getLstSanPhamChiTietExist();
	}

	@Override
	public <S extends SanPhamChiTiet> S save(S entity) {
		return sanPhamChiTietRepository.save(entity);
	}

	@Override
	public Optional<SanPhamChiTiet> findById(Long id) {
		return sanPhamChiTietRepository.findById(id);
	}

	@Override
	public List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(Long id) {
		return sanPhamChiTietRepository.getLstSanPhamChiTietBySanPhamId(id);
	}

	@Override
	public List<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data) {
		return sanPhamChiTietRepository.searchProductDetailExist(data);
	}
}
