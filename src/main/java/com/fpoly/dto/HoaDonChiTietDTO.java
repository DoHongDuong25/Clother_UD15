package com.fpoly.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietDTO extends BaseDTO<HoaDonChiTietDTO> {

	    private Long hoaDonId;

	    private Long sanPhamChiTietId;

	    private BigDecimal donGia;

	    private int soLuong;

	    private BigDecimal tongTien;

	    private int daXoa;
}
