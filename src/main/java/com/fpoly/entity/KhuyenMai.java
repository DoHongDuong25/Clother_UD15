package com.fpoly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giam_gia")
public class KhuyenMai extends BaseEntity implements Serializable {

    @Column(name = "ten_khuyen_mai",columnDefinition = "nvarchar(256) not null unique")
    private String tenKhuyenMai;

    @Column(name = "ngay_bat_dau",columnDefinition = "Date not null unique")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc",columnDefinition = "Date not null unique")
    private Date ngayKetThuc;

    @Column(name = "phan_tram_giam",columnDefinition = "int not null unique")
    private String phanTramGiam;

    @Column(name = "gia_tri_toi_thieu",columnDefinition = "int not null unique")
    private int giaTriToiThieu;

}
