package com.fpoly.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
	private Long id;
	
	private Long kieuDangId;
	
	private Long chatLieuId;
	
	private Long loaiHangId;
	
	private Long phongCachId;
	
	private String tenSanPham;
	
	private BigDecimal gia;

	private String moTa;

	private Boolean daXoa;
}
