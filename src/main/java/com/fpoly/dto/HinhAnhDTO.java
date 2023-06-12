package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO extends BaseDTO{
	private Long id;
	
	private Long sanPhamChiTietId;
	
	private String tenAnh;
	
	private Boolean laAnhChinh;

	private Boolean coHienThi;
}
