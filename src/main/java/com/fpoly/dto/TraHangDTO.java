package com.fpoly.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraHangDTO {
    private Long tra_hang_id;

    private Long khach_hang_id;


    private String ngay_tra;

    private BigDecimal tong_tien;

    private String ghi_chu;

    private Long ly_do_tra_hang_id;

    private String nguoi_tao;

    private Date ngay_tao;

    private String nguoi_chinh_sua;

    private Date ngay_chinh_sua;
}
