package com.fpoly.dto.composite;

import java.math.BigDecimal;
import java.util.List;

import com.fpoly.entity.KichCo;
import com.fpoly.entity.MauSac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSanPhamTaiQuayDTO {
	private Long sanPhamId;
	
	private String tenSanPham;
	
	private BigDecimal gia;
	
	private List<HinhAnhSanPhamChiTietDTO> hinhAnhSanPhamChiTietDTO;
	
	private List<String> anhChinhs;
	
	private List<KichCo> lstKichCo;
	
	private List<MauSac> lstMauSac;
	
}
