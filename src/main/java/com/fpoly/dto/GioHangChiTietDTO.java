package com.fpoly.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GioHangChiTietDTO {
    private Long gio_hang_chi_tiet_id;

    private Long san_pham_chi_tiet_id;

    private Long gio_hang_id;

    private int soLuong;

    private int don_gia;

    private BigDecimal thanhTien;

    private int trangThai;

    private Boolean da_xoa;
}
