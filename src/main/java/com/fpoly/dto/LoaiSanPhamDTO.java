package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiSanPhamDTO extends BaseDTO{
	private Long id;
	
	private String tenLoaiSanPham;
	
	private Boolean daXoa;
}
