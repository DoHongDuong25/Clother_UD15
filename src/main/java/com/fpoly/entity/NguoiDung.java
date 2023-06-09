package com.fpoly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private List<GiaoDich> giaoDichs = new ArrayList<GiaoDich>() ;

}
