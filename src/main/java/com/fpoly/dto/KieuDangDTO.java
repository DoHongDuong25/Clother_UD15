package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KieuDangDTO {
	private Long kieu_dang_id;
	
	private String ten_kieu_dang;
	
	private String nguoi_tao;
	
	private Date ngay_tao;
	
	private String nguoi_chinh_sua;
	
	private Date ngay_chinh_sua;
}
