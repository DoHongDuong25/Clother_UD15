package com.fpoly.dto.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPAndSPCTSearchDto {
	
	private List<Long> kieuDangIds;
	
	private List<Long> chatLieuIds;
	
	private List<Long>  loaiSanPhamIds;
	
	private List<Long>  phongCachIds;
	
	private List<Long> kichCoIds;
	
	private List<Long> mauSacIds;
	
	private String tenSanPham;
	
	private BigDecimal giaHienHanhMin;
	
	private BigDecimal giaHienHanhMax;
	
	private Integer soLuongMin;
	
	private Integer soLuongMax;
	
	private List<Boolean> coHienThi;
	
	private String nguoiTaoSPCT;
	
	private String nguoiCapNhatSPCT;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayTaoMin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayTaoMax;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayCapNhatMin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayCapNhatMax;
}
