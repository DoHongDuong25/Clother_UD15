package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class BaseDTO<DTO> {
	private Long id ;
	
	private Date ngayTao;
	
	private String nguoiTao;
	
	private Date ngayCapNhat;
	
	private String nguoiCapNhat;
}
