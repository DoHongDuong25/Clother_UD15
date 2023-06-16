package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nguoi_dung")
@EntityListeners(AuditingEntityListener.class)
public class NguoiDung extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -6627502088104297623L;

	
	@Column(name = "ten_dang_nhap",columnDefinition = "nvarchar(256) not null unique")
	private String tenDangNhap;
	
	@Column(name = "mat_khau",columnDefinition = "nvarchar(256) not null")
	private String matKhau;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String email;
	
	@Column(name = "ten_nguoi_dung", columnDefinition = "nvarchar(200) not null")
	private String tenNguoiDung;

	@Column(name = "so_dien_thoai",columnDefinition = "nvarchar(20) not null")
	private String soDienThoai;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "trang_thai", columnDefinition = "int default(0)")
	private int trangThai;

	@Column(name = "da_xoa")
	private Boolean daXoa;
	
	@OneToMany(mappedBy="nguoiDung")
	private List<GiaoDich> giaoDichs = new ArrayList<>() ;
	

	
}
