package com.fpoly.dto.composite;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Primary;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.dto.SanPhamChiTietDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhMauSacDTO {
	private Long mauSacAddImagesId;
	
	private List<MultipartFile> imgFiles;
	
	private Integer laAnhChinh;
	
	private String tenMauSacAddImg;

	private Integer coHienThiHinhAnh;
}
