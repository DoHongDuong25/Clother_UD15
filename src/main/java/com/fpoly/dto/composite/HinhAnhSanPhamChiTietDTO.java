package com.fpoly.dto.composite;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.dto.SanPhamChiTietDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhSanPhamChiTietDTO {
	private Long spctId;
		
	private List<MultipartFile> imgFiles;
	
	private Boolean laAnhChinh;

	private Boolean coHienThi;
	
	private Boolean isOpenPopupAddImage = false;
}
