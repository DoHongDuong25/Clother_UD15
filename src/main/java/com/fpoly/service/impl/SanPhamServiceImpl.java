package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fpoly.entity.SanPham;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.SanPhamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SanPhamServiceImpl implements SanPhamService{
	private final SanPhamRepository sanPhamRepository;
	
	@Override
	public List<SanPham> selectSanPhamExistById(Long id) {
		return sanPhamRepository.selectSanPhamExistById(id);
	}
	
	@Override
	public <S extends SanPham> S save(S entity) {
		return sanPhamRepository.save(entity);
	}

	@Override
	public Optional<SanPham> findById(Long id) {
		return sanPhamRepository.findById(id);
	}

	@Override
	public List<SanPham> selectSanPhamExist() {
		return sanPhamRepository.selectSanPhamExist();
	}
}
