package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiaChiDTO extends BaseDTO<DiaChiDTO> {
	
	private String diaChi;
	
	private String city ;
	
	private String district ;
	
	private String ward ;
	
	private String soNha ;
	
	private Long khachHangId ;
}
