package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO extends BaseDTO<HinhAnhDTO> {
	
	private Long sanPhamChiTietId;
	
	private String tenAnh;
	
	private short laAnhChinh;

}
