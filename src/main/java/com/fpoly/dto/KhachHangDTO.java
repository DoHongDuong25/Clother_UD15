package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO extends BaseDTO<KhachHangDTO> {
		
	
	    private String email;

	    private String matKhau;

	    private String hoTen;

	    private int soLanMua;
	    
	    private String soDienThoai;

	    private int trangThai;
	    
	    
	    
	    
	    private List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
	    
		private List<DiaChiDTO> listDiaChi = new ArrayList<DiaChiDTO>();
}
