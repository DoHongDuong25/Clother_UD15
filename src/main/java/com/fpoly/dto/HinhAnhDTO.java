package com.fpoly.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO extends BaseDTO{
	
	private Long mauSacId;
	
	private Long sanPhamId;
	
	private String tenAnh;
	
	private MultipartFile imgFile;
	
	private Boolean laAnhChinh;

	private Boolean coHienThi;
}
