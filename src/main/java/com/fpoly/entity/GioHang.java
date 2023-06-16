package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data@Entity
@Table(name = "gio_hang")
@EqualsAndHashCode(callSuper=false)
public class GioHang extends BaseEntity implements Serializable {
    

    @Column(name = "ngay_tao",columnDefinition = "nvarchar(256)  ")
    @CreatedDate
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat",columnDefinition = "nvarchar(256)  ")
    private Date ngayCapNhat;

    @OneToOne
    @JoinColumn(name = "khach_hang_id", insertable = false, updatable = false)
    private KhachHang khachHang;

    @Column(name = "trang_thai",columnDefinition = "int not null")
    private int trangThai;

    @Column(name = "tong_tien", columnDefinition = "int")
    private int tongTien;
    
    @OneToMany(mappedBy="gioHang")
    private List<GioHangChiTiet> gioHangChiTiets = new ArrayList<GioHangChiTiet>();
}
