package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KhuyenMaiDTO extends BaseDTO<KhuyenMaiDTO> {

    @NotBlank
    private String tenKhuyenMai;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;

    @Min(0)
    private int phanTramGiam;
    @Min(0)
    private int giaTriToiThieu;

    private boolean trangThai;

}
