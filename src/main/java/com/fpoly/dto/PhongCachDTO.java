package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhongCachDTO {
	private Long phong_cach_id;
	
	private String ten_phong_cach;
	
	private String nguoi_tao;
	
	private Date ngay_tao;
	
	private String nguoi_chinh_sua;
	
	private Date ngay_chinh_sua;
}
