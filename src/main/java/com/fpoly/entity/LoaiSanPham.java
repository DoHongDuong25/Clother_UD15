package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loai_san_pham")
@EntityListeners(AuditingEntityListener.class)
public class LoaiSanPham extends BaseEntity implements Serializable{
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String tenLoaiSanPham;
	
	@OneToMany(mappedBy = "loaiSanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SanPham> sanPhams;
	
	@Column
	private Boolean daXoa;
	
	
}
