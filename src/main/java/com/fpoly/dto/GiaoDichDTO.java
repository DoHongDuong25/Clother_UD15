package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiaoDichDTO extends BaseDTO<GiaoDichDTO> {


    private Long nguoiDungId;



    private int trangThai;
}
