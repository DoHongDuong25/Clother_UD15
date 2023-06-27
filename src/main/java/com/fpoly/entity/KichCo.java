package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kich_co")
public class KichCo extends BaseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "kichCo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SanPhamChiTiet> SanPhamChiTiets;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String tenKichCo;
	
	@Column
	private Boolean daXoa;

	@Override
	public String toString() {
		return "KichCo{" +
				"SanPhamChiTiets=" + SanPhamChiTiets.size() +
				", tenKichCo='" + tenKichCo + '\'' +
				", daXoa=" + daXoa +
				'}';
	}
}
