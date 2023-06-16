package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO extends BaseDTO<GioHangDTO> {


	private Long khachHangId;

	private int trangThai;

	private int tongTien;
}
