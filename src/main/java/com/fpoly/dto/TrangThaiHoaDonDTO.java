package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrangThaiHoaDonDTO {
    private Long trang_thai_hoa_don_id;

    private Long hoa_don_id;

    private Date ngayTao;

    private String nguoiTao;

    private Date ngayChinhSua;

    private String nguoiChinhSua;

    private int trang_thai;
}
