package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fpoly.entity.HinhAnh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO{

	private Long sanPhamId;
	
	private Long kichCoId;

	private Long mauSacId;
	
	@NotNull(message = "Số lượng không được để trống")
	@Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
	private Integer soLuong;
	
	private List<HinhAnhDTO> hinhAnhs = new ArrayList<HinhAnhDTO>();
	
	private Boolean daXoa;
}
