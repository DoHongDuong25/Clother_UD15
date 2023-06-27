package com.fpoly.entity;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> Hung_Voucher

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
