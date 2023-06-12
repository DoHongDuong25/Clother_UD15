package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichCoDTO extends BaseDTO{
	private Long id;

	private String tenKichCo;
	
	private Boolean daXoa;
}
