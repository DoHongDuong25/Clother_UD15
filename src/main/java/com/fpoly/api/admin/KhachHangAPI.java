package com.fpoly.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.dto.KhachHangDTO;
import com.fpoly.service.KhachHangService;

@RestController(value="khachHangAPIOfAdmin")
public class KhachHangAPI {
	@Autowired
	private KhachHangService KhachHangService ;
	
	
	@GetMapping("/admin/api/khach-hang")
	public List<KhachHangDTO> layDanhSachKhachHang(){
		return KhachHangService.findAll();
	}
	
	@PutMapping("/admin/api/khach-hang/trang-thai-dang-hoat-dong")
	public void deleteKhachHangByTrangThaiDangHoatDong(@RequestBody long[] ids) {
			KhachHangService.capNhatTrangThaiThanhDangHoatDongTheoMa(ids);
	}
	
	
	@PutMapping("/admin/api/khach-hang/trang-thai-khong-hoat-dong")
	public void deleteKhachHangByTrangThaiKhongHoatDong(@RequestBody long[] ids) {
			KhachHangService.capNhatTrangThaiThanhKhongHoatDongTheoMa(ids);
	}
	
	@PostMapping("/admin/api/khach-hang")
	public KhachHangDTO themMoiKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
		return KhachHangService.save(khachHangDTO);
	}
	@PutMapping("/admin/api/khach-hang")
	public KhachHangDTO capNhatKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
		return KhachHangService.save(khachHangDTO);
	}
}
