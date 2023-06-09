package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LyDoTraHangDTO {
    private Long ly_do_tra_hang_id;

    private String ly_do;

    private Long hinh_anh_id;
}
