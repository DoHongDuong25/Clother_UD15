package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiaoDichDTO {
    private Long giao_dich_id;


    private Long nguoi_dung_id;

    private String ngay_tao;

    private String ngay_cap_nhat;

    private String nguoi_cap_nhat;

    private int trang_thai;
}
