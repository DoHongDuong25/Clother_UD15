package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import com.fpoly.entity.HinhAnh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO{
	private Long id;

	private Long sanPhamId;
	
	private Long kichCoId;

	private Long mauSacId;
	
	private int soLuong;
	
	private List<HinhAnhDTO> hinhAnhs = new ArrayList<HinhAnhDTO>();
	
	private Boolean daXoa;
}
