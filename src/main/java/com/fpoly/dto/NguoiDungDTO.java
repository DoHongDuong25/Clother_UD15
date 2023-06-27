package com.fpoly.dto;

<<<<<<< HEAD
import java.util.Date;

//import com.fpoly.entity.Role;

=======
import com.fpoly.entity.Role;
>>>>>>> Hung_Voucher
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO extends BaseDTO<NguoiDungDTO> {
	
	private String tenDangNhap;
	
	private String matKhau;
	
	private String email;
	
	private String tenNguoiDung;

	private String soDienThoai;
	
//	private Role role;

	private int trangThai;

	private Boolean daXoa;
	
	
}
