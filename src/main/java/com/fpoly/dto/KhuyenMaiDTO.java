package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KhuyenMaiDTO extends BaseDTO<KhuyenMaiDTO> {

    private String tenKhuyenMai;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date ngayBatDau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date ngayKetThuc;

    private int phanTramGiam;

    private int giaTriToiThieu;

    private boolean trangThai;

}
