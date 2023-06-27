package com.fpoly.entity;

<<<<<<< HEAD
import lombok.*;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
=======
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
>>>>>>> Hung_Voucher
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
public class KhachHang extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "email",columnDefinition = "nvarchar(256) not null unique")
    private String email;

<<<<<<< HEAD
    @Column(name = "mat_Khau",columnDefinition = "nvarchar(256) not null ")
=======
    @Column(name = "mat_Khau",columnDefinition = "nvarchar(256) null unique")
>>>>>>> dai
    private String matKhau;

    @Column(name = "ho_ten",columnDefinition = "nvarchar(256) not null ")
    private String hoTen;
    
    @Column(name = "so_lan_mua",columnDefinition = "int not null ")
    private int soLanMua;

    @Column(name = "so_dien_thoai",columnDefinition = "nvarchar(256) not null ")
    private String soDienThoai;

    @Column(name = "trang_thai",columnDefinition = "int not null ")
    private int trangThai;
<<<<<<< HEAD

    @Override
    public String toString() {
        return "KhachHang{" +
                "email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soLanMua=" + soLanMua +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
=======
    
    @OneToMany(mappedBy="khachHang")
    private List<DiaChi> listDiaChi = new ArrayList<DiaChi>();
    
>>>>>>> Hung_Voucher
}
