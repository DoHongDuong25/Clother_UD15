package com.fpoly.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO extends BaseDTO<BaseDTO> {
	
	private Long mauSacId;
	
	private Long sanPhamId;
	
	private String tenAnh;
	
	private Boolean laAnhChinh;

	private Boolean coHienThi;
}
