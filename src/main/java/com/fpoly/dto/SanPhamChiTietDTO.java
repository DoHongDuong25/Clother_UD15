package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
	private Long san_pham_chi_tiet_id;

	private Long san_pham_id;

	private Long kich_co_id;

	private Long mau_sac_id;
	
	private int soLuong;
	
	private Boolean trangThai;
}
