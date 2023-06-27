package com.fpoly.service;

import java.util.Optional;

import com.fpoly.entity.HinhAnh;

public interface HinhAnhService {

	void delete(HinhAnh entity);

	Optional<HinhAnh> findById(Long id);

	<S extends HinhAnh> S save(S entity);

}
