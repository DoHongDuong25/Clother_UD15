package com.fpoly.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
    private long hoa_don_id;

    private Long khuyen_mai_id;

    private Long nguoi_dung_id;

    private Long khach_hang_id;

    private Long giao_dich_id;

    private String nguoi_nhan;

    private String sdt_nguoi_nhan;

    private String dia_chi_giao_hang;

    private String thoi_gian_giao_hang;

    private String ghi_chu;

    private BigDecimal tong_tien_hoa_don;

    private BigDecimal tien_ship;

    private BigDecimal tong_tien_don_hang;

    private String loai_hoa_don;

    private Date ngayTao;

    private String nguoiTao;

    private Date ngayChinhSua;

    private String nguoiChinhSua;
}
