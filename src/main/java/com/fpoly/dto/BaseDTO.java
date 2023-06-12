package com.fpoly.dto;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {
	private Long id ;
	
	private Date ngayTao;
	
	private String nguoiTao;
	
	private Date ngayCapNhat;
	
	private String nguoiCapNhat;
}
