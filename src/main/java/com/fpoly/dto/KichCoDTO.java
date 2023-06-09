package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichCoDTO {
	private Long kich_co_id;

	
	private String ten_kich_co;

	private String nguoi_tao;

	private Date ngay_tao;

	private String nguoi_chinh_sua;

	private Date ngay_chinh_sua;
}
