package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO {
	    private Long gio_hang_id;

	    private Date ngayTao;

	    private Date ngayCapNhat;

	    private Long khach_hang_id;

	    private int trangThai;

	    private int tong_tien;
}
