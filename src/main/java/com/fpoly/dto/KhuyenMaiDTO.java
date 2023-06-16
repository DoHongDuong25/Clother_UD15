package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiDTO extends BaseDTO<KhuyenMaiDTO> {

    private String tenKhuyenMai;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private int phanTramGiam;

    private int giaTriToiThieu;


}
