package com.fpoly.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hinh_anh")
public class HinhAnh extends BaseEntity implements Serializable{

	@ManyToOne
	@JoinColumn(name = "san_pham_chi_tiet_id")
	private SanPhamChiTiet sanPhamChiTiet;
	
	@Column(name = "ten_anh", columnDefinition = "nvarchar(200) not null")
	private String tenAnh;
	
	@Column(name = "is_anh_chinh")
	private Boolean isAnhChinh;
	
	@Column(name = "co_hien_thi")
	private Boolean coHienThi;
	
	@OneToMany(mappedBy = "hinhAnhTraHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<LyDoTraHang> lyDoTraHang;

	@Override
	public String toString() {
		return "HinhAnh{" +
				"sanPhamChiTiet=" + sanPhamChiTiet.getId() +
				", tenAnh='" + tenAnh + '\'' +
				", isAnhChinh=" + isAnhChinh +
				", coHienThi=" + coHienThi +
				", lyDoTraHang=" + lyDoTraHang.size() +
				'}';
	}
}
