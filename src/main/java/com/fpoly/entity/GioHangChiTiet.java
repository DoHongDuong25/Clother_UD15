package com.fpoly.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet extends BaseEntity implements Serializable {
   

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id", insertable = false, updatable = false)
    private SanPhamChiTiet sanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id", insertable = false, updatable = false)
    private GioHang gioHang;

    @Column(name = "so_luong", columnDefinition = "int not null")
    private int soLuong;

    @Column(name = "don_gia", columnDefinition = "int")
    private int donGia;

    @Column(name = "tong_tien", columnDefinition = "varchar(50) not null")
    private BigDecimal thanhTien;

    @Column(name = "trang_thai", columnDefinition = "int default(0) not null")
    private int trangThai;

    @Column(name = "da_xoa", columnDefinition = "Bit")
    private Boolean daXoa ;
}
