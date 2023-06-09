package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiHangDTO {
	private Long loai_id;
	
	private String ten_loai;
	
	private Boolean trang_thai;
	
	private String nguoi_tao;

	private Date ngay_tao;
	
	private String nguoi_chinh_sua;
	
	private Date ngay_chinh_sua;
}
