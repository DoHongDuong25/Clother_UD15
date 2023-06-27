package com.fpoly.dto;


import java.util.ArrayList;
import java.util.List;

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
	
    private int page ;
	    
	private int limit ;
	    
	private int totalPages; 
	    
	private int totalItems;
	
	private List<DiaChiDTO> listDiaChi = new ArrayList<DiaChiDTO>();
	
	private Long khachHangId ;
}
