package com.fpoly.dto.composite;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.MauSac;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SPTaiQuayDTO {
	private List<ShowSanPhamTaiQuayDTO> lstShowSanPhamTaiQuayDTO;
	
	private Long kichCoId;
	
	private Long mauSacId;
	
	private Integer soLuong;
}

