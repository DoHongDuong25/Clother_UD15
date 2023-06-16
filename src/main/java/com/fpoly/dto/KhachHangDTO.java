package com.fpoly.dto;

import com.fpoly.entity.DiaChi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO extends BaseDTO<KhachHangDTO> {

	    private String email;

	    private String matKhau;

	    private String hoTen;

	    private int soLanMua;

	    private String soDienThoai;

	    private int trangThai;
	    
		private String nguoiTao;
		
		private Date ngayTao;
		
		private String nguoiChinhSua;
		
		private Date ngayChinhSua;
		
		private List<DiaChi> listDiaChi = new ArrayList<DiaChi>();
}
