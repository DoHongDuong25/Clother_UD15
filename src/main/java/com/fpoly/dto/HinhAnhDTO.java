package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO {
	private Long hinh_anh_id;
	
	private Long san_pham_chi_tiet_id;
	
	private String ten_anh;
	
	private short la_anh_chinh;

}
