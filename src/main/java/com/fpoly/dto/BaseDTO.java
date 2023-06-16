package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BaseDTO<DTO> {
	private Long id ;
	
	private Date ngayTao;
	
	private String nguoiTao;
	
	private Date ngayCapNhat;
	
	private String nguoiCapNhat;
}
