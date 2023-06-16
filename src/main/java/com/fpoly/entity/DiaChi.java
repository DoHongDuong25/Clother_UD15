package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dia_chi")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
public class DiaChi extends BaseEntity implements Serializable {
	
	
	@Column(name = "dia_chi",columnDefinition = "nvarchar(255) not null")
	private String diaChi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="khach_hang_id")
	private KhachHang khachHang ;
	
	
}
