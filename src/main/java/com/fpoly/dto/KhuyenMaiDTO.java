package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhuyenMaiDTO extends BaseDTO<KhuyenMaiDTO> {

    private String tenKhuyenMai;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private int phanTramGiam;

    private int giaTriToiThieu;

    private boolean trangThai;

}
