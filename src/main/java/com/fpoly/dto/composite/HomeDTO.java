package com.fpoly.dto.composite;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.entity.HinhAnh;
import com.fpoly.entity.MauSac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO {
	private List<HinhAnh> lstAnhChinh;
	
	private List<HinhAnhDTO> lstAnhChinhByMauSac;
	
	private List<MauSac> lstMauSac;
	
	
}
