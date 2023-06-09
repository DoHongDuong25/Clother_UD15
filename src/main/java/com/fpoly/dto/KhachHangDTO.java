package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO {
	    private Long khach_hang_id;

	    private String email;

	    private String mat_Khau;

	    private String hoTen;

	    private int soLanMua;

	    private String soDienThoai;

	    private int trangThai;
	    
		private String nguoi_tao;
		
		private Date ngay_tao;
		
		private String nguoi_chinh_sua;
		
		private Date ngay_chinh_sua;
}
