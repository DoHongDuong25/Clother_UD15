package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhongCachDTO extends BaseDTO{
	private Long id;
	
	private String tenPhongCach;
	
	private Boolean daXoa;
}
