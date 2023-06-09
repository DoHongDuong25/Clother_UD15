package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiDTO {
    private long giam_gia_id;

    private String ten_khuyen_mai;

    private Date ngay_bat_dau;

    private Date ngay_ket_thuc;

    private int phan_tram_giam;

    private int giaTriToiThieu;

    private Date ngayTao;

    private String nguoiTao;

    private Date ngayChinhSua;

    private String nguoiChinhSua;
}
