package com.fpoly.service.impl;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fpoly.entity.HinhAnh;
import com.fpoly.repository.HinhAnhRepository;
import com.fpoly.service.HinhAnhService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class HinhAnhServiceImpl implements HinhAnhService{
	private HinhAnhRepository hinhAnhRepository;

	@Override
	public <S extends HinhAnh> S save(S entity) {
		return hinhAnhRepository.save(entity);
	}

	@Override
	public Optional<HinhAnh> findById(Long id) {
		return hinhAnhRepository.findById(id);
	}

	@Override
	public void delete(HinhAnh entity) {
		entity.setDaXoa(true);
		hinhAnhRepository.save(entity);
	}
	
	
}