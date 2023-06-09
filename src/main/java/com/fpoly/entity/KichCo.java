package com.fpoly.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kich_co")
public class KichCo extends BaseEntity implements Serializable{

	@OneToMany(mappedBy = "kichCo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SanPhamChiTiet> SanPhamChiTiets;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String tenKichCo;
	
	@Column
	private Boolean daXoa;

	
}
