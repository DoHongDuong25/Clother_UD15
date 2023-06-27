package com.fpoly.entity;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
>>>>>>> Hung_Voucher
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham_chi_tiet")
@EqualsAndHashCode(callSuper=false)
public class SanPhamChiTiet extends BaseEntity implements Serializable{
	@ManyToOne
	@JoinColumn(name = "kich_co_id", nullable = false)
	private KichCo kichCo;
	
	@ManyToOne
	@JoinColumn(name = "mau_sac_id", nullable = false)
	private MauSac mauSac;
	
	@ManyToOne
	@JoinColumn(name = "san_pham_id", nullable = false)
	private SanPham sanPham;
	
	@OneToMany(mappedBy = "sanPhamChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HinhAnh> hinhAnhs = new ArrayList<>();
	
	@Column
	private int soLuong;
	
	@OneToMany(mappedBy = "sanPhamChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HoaDonChiTiet> hoaDonChiTiets;
	
	@Column
	private Boolean coHienThi;
	
	@Column
	private Boolean daXoa;
	
	@OneToMany(mappedBy="sanPhamChiTiet")
<<<<<<< HEAD
	private List<GioHangChiTiet> gioHangChiTiet = new ArrayList<GioHangChiTiet>();

	@Override
	public String toString() {
		return "SanPhamChiTiet{" +
				"kichCo=" + kichCo.getId() +
				", mauSac=" + mauSac.getId() +
				", sanPham=" + sanPham.getId() +
				", hinhAnhs=" + hinhAnhs.size() +
				", soLuong=" + soLuong +
				", hoaDonChiTiets=" + hoaDonChiTiets.size() +
				", coHienThi=" + coHienThi +
				", daXoa=" + daXoa +
				", gioHangChiTiet=" + gioHangChiTiet.size() +
				'}';
	}
=======
	private List<GioHangChiTiet> gioHangChiTiet = new ArrayList<>();
	
>>>>>>> Hung_Voucher
}
