package com.fpoly.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraHangDTO extends BaseDTO<TraHangDTO> {

    private Long khachHangId;

    private String ngayTra;

    private BigDecimal tongTien;

    private String ghiChu;

    private Long lyDoTraHang;

    
}
