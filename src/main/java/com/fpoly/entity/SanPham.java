package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham")
@EntityListeners(AuditingEntityListener.class)
public class SanPham extends BaseEntity implements Serializable{
	@ManyToOne
	@JoinColumn(name = "kieu_dang_id", nullable = false)
	private KieuDang kieuDang;
	
	@ManyToOne
	@JoinColumn(name = "chat_lieu_id", nullable = false)
	private ChatLieu chatLieu;
	
	@ManyToOne
	@JoinColumn(name = "loai_san_pham_id", nullable = false)
	private LoaiSanPham loaiSanPham;
	
	@ManyToOne
	@JoinColumn(name = "phong_cach_id", nullable = false)
	private PhongCach phongCach;
	
	@Column
	private Boolean daXoa;
	
	@OneToMany(mappedBy = "sanPham",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SanPhamChiTiet> sanPhamChiTiets = new ArrayList<>();
	
	@Column(columnDefinition = "nvarchar(256)", nullable = false)
	private String tenSanPham;
	
	@Column(columnDefinition = "nvarchar(512)", nullable = true)
	private String moTa;
	
	@Column(precision = 10)
	private BigDecimal giaHienHanh;
}
