package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO<SanPhamChiTietDTO> {
	

	private Long sanPhamId;

	private Long kichCoId;

	private Long mauSacId;
	
	private int soLuong;
	
	private Boolean trangThai;
}
