package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KieuDangDTO extends BaseDTO<KieuDangDTO> {
	
	private String tenKieuDang;
	
}
