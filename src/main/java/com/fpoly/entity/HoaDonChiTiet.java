package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet extends BaseEntity implements Serializable {
    

    @ManyToOne
    @JoinColumn(name = "hoa_don_id", insertable = false, updatable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "don_gia", columnDefinition = "int not null")
    private BigDecimal donGia;

    @Column(name = "so_luong", columnDefinition = "int not null")
    private int soLuong;

    @Column(name = "tong_tien", columnDefinition = "int not null")
    private BigDecimal tongTien;

    @Column(name = "da_xoa", columnDefinition = "BIT")
    private int daXoa;
    
    
    @OneToMany(mappedBy = "hoaDonChiTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TraHang> traHangs = new ArrayList<TraHang>();
    
}
