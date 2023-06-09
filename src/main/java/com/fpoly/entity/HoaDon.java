package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
@EntityListeners(AuditingEntityListener.class)
public class HoaDon extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6627502088104297623L;
    

    @OneToOne
    @JoinColumn(name = "khuyen_mai_id", insertable = false, updatable = false)
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", insertable = false, updatable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "giao_dich_id", insertable = false, updatable = false)
    private GiaoDich giaoDich;

    @Column(name = "nguoi_nhan", columnDefinition = "nvarchar(50) not null")
    private String nguoiNhan;

    @Column(name = "so_dien_thoai_nguoi_nhan", columnDefinition = "nvarchar(50) not null")
    private String sdtNguoiNhan;

    @Column(name = "dia_chi_giao_hang", columnDefinition = "nvarchar(50) not null")
    private String diaChiGiaoHang;

    @Column(name = "thoi_gian_giao_hang", columnDefinition = "nvarchar(50) not null")
    private String thoiGianGiaoHang;

    @Column(name = "ghi_chu", columnDefinition = "nvarchar(50) not null")
    private String ghiChu;

    @Column(name = "tong_tien_hoa_don", columnDefinition = "int not null")
    private BigDecimal tongTienHoaDon;

    @Column(name = "tien_ship", columnDefinition = "int not null")
    private BigDecimal tienShip;

    @Column(name = "tong_tien_don_hang", columnDefinition = "int not null")
    private BigDecimal tongTienDonHang;

    @Column(name = "loai_hoa_don", columnDefinition = "nvarchar(50) not null")
    private String loaiDonHang;
    
    @OneToMany(mappedBy="hoaDon")
	private List<TrangThaiHoaDon> trangThaiHoaDons = new ArrayList<TrangThaiHoaDon>() ;
    
    
    
}
