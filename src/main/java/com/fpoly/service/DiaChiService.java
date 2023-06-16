package com.fpoly.service;

import com.fpoly.dto.DiaChiDTO;

public interface DiaChiService {

	DiaChiDTO save(DiaChiDTO diaChiDTO);

	void delete(long[] ids);

}
