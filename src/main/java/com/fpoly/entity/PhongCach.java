package com.fpoly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phong_cach")
@EntityListeners(AuditingEntityListener.class)
public class PhongCach extends BaseEntity implements Serializable{
	@Column(name = "ten_phong_cach",columnDefinition = "nvarchar(50) not null")
	private String tenPhongCach;
		
	@OneToMany(mappedBy = "phongCach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SanPham> sanPhams;
	
	@Column
	private Boolean daXoa;
}
