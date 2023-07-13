package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

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

	    private Integer soLanMua;
	    
	    private String soDienThoai;

	    private Integer trangThai  = 2;
	    
	    private int page ;
	    
	    private int limit ;
	    
	    private int totalPages; 
	    
	    private int totalItems;
	    
	    private String input = "" ;
	    
	    @NotBlank(message="Chưa chọn thành phố !")
	    private String city ;

	    @NotBlank(message="Chưa chọn quận huyện !")
	    private String district ;

	    @NotBlank(message="Chưa chọn xã !")
	    private String ward ;

	    @NotBlank(message="Vui lòng nhập số nhà !")
	    private String soNha ;
	    
	    private List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
	    
		private List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
}
