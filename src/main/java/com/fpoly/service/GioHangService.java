package com.fpoly.service;

import com.fpoly.dto.GioHangDTO;

public interface GioHangService {

	GioHangDTO findByKhachHangId(Long i);

	void capNhatTongTien(Long i);


}
