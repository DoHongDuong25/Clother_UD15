package com.fpoly.service;

import java.util.List;

import com.fpoly.dto.GioHangChiTietDTO;

public interface GioHangChiTietService  {

	List<GioHangChiTietDTO> findAllByGioHangId(Long id);

	void capNhatSoLuongGioHangChiTiet(Long[] ids, Integer[] soLuongs);

	void deleteById(Long id);

	void deleteAll();

}
