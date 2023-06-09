package com.fpoly.dto;

import java.util.Date;

import com.fpoly.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO {
	private Long nguoi_dung_id;
	
	private String ten_dang_nhap;
	
	private String mat_khau;
	
	private String email;
	
	private String ten_nguoi_dung;

	private String so_dien_thoai;
	
	private Role role;

	private int trang_thai;

	private Boolean da_xoa;
	
	private String nguoi_tao;
	
	private Date ngay_tao;
	
	private String nguoi_chinh_sua;
	
	private Date ngay_chinh_sua;
}
