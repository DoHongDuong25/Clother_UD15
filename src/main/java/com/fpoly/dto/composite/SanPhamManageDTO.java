package com.fpoly.dto.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fpoly.dto.SanPhamChiTietDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamManageDTO {
	private Long sanPhamId;
	
	private Boolean isAnhChinh;
	
	private String tenAnh;
	
	@Min(value = 0, message = "Kiểu dáng sản phẩm không được để trống")
	private Long kieuDangId;
	
	@Min(value = 0, message = "Chất liệu sản phẩm không được để trống")
	private Long chatLieuId;
	
	@Min(value = 0, message = "Loại sản phẩm không được để trống")
	private Long loaiSanPhamId;
	
	@Min(value = 0, message = "Phong cách sản phẩm không được để trống")
	private Long phongCachId;
	
	private Boolean daXoa;
	
	@NotEmpty(message = "Tên sản phẩm không được để trống")
	private String tenSanPham;
	
	@NotEmpty(message = "Mô tả sản phẩm không được để trống")
	private String moTa;
	
	@DecimalMin(value = "1", message = "Giá hiện hành không được để trống")
	@NotNull(message = "Giá hiện hành không được để trống")
	private BigDecimal giaHienHanh;
	
	@NotEmpty(message = "Danh sách kích cỡ sản phẩm không được để trống")
	private List<@Valid Long> kichCoIds;
	
	@NotEmpty(message = "Danh sách màu sắc sản phẩm không được để trống")
	private List<@Valid Long> mauSacIds;
	//@param idSPCT, MultipartFile
	private HashMap<Long, MultipartFile> imgFiles = new HashMap<Long, MultipartFile>();
		
	private List<SanPhamChiTietDTO> spctDTO = new ArrayList<SanPhamChiTietDTO>();
	
	private Boolean isEdit = false;
}
