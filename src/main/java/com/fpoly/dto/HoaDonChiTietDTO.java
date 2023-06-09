package com.fpoly.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietDTO {
	    private Long hoa_don_chi_tiet_id;

	    private Long hoa_don_id;

	    private Long san_pham_chi_tiet_id;

	    private BigDecimal don_gia;

	    private int so_luong;

	    private BigDecimal tong_tien;

	    private int da_xoa;
}
