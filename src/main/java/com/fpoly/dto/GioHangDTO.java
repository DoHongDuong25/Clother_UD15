package com.fpoly.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fpoly.entity.DiaChi;
import com.fpoly.entity.GioHangChiTiet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class GioHangDTO extends BaseDTO<GioHangDTO> {



	    private Long khachHangId;

	    private int trangThai;

	    private int tongTien;
	    
	    private List<GioHangChiTietDTO> listGioHangChiTiets = new ArrayList<GioHangChiTietDTO>();

		
}
