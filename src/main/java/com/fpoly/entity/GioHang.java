package com.fpoly.entity;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gio_hang")
public class GioHang extends BaseEntity implements Serializable {
    

    @Column(name = "ngay_tao",columnDefinition = "nvarchar(256)  unique")
    @CreatedDate
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat",columnDefinition = "nvarchar(256)  unique")
    private Date ngayCapNhat;

    @OneToOne
    @JoinColumn(name = "khach_hang_id", insertable = false, updatable = false)
    private KhachHang khachHang;

    @Column(name = "trang_thai",columnDefinition = "int not null unique")
    private int trangThai;

    @Column(name = "tong_tien", columnDefinition = "int")
    private int tongTien;
    
    @OneToMany(mappedBy="gioHang")
    private List<GioHangChiTiet> gioHangChiTiets = new ArrayList<GioHangChiTiet>();

    @Override
    public String toString() {
        return "GioHang{" +
                "ngayTao=" + ngayTao +
                ", ngayCapNhat=" + ngayCapNhat +
                ", khachHang=" + khachHang.getId() +
                ", trangThai=" + trangThai +
                ", tongTien=" + tongTien +
                ", gioHangChiTiets=" + gioHangChiTiets.size() +
                '}';
    }
}
