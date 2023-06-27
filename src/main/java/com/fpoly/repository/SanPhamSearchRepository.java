package com.fpoly.repository;

import java.util.List;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPham;

public interface SanPhamSearchRepository {

	List<SanPham> searchProductExist(SPAndSPCTSearchDto data);
	
}
