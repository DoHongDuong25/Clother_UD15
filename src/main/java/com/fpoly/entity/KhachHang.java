package com.fpoly.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
@EntityListeners(AuditingEntityListener.class)
public class KhachHang extends BaseEntity implements Serializable {

    @Column(name = "email",columnDefinition = "nvarchar(256) not null unique")
    private String email;

    @Column(name = "mat_Khau",columnDefinition = "nvarchar(256) not null unique")
    private String matKhau;

    @Column(name = "ho_ten",columnDefinition = "nvarchar(256) not null unique")
    private String hoTen;

    @Column(name = "so_lan_mua",columnDefinition = "int not null unique")
    private int soLanMua;

    @Column(name = "so_dien_thoai",columnDefinition = "nvarchar(256) not null unique")
    private String soDienThoai;

    @Column(name = "trang_thai",columnDefinition = "int not null unique")
    private int trangThai;

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
}
