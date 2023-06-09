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
	private Long san_pham_id;

	
	
	private Long kieu_dang_id;
	
	private Long chat_lieu_id;
	
	private Long loai_hang_id;
	
	private Long phong_cach_id;
	
	private String tenSanPham;
	
	private BigDecimal gia_ban;

	private String mo_ta;

	private String nguoiTao;
	
	private Date ngayTao;
	
	private String nguoiChinhSua;
	
	private Date ngayChinhSua;

	private Boolean da_xoa;
}
