package com.fpoly.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.convertor.GioHangChiTietConvertor;
import com.fpoly.convertor.GioHangConvertor;
import com.fpoly.convertor.SanPhamChiTietConvertor;
import com.fpoly.convertor.SanPhamConvertor;
import com.fpoly.dto.GioHangChiTietDTO;
import com.fpoly.dto.GioHangDTO;
import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.dto.SanPhamDTO;
import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.repository.GioHangChiTietRepository;
import com.fpoly.repository.GioHangRepository;
import com.fpoly.service.GioHangService;

@Service
public class GioHangServiceImpl implements GioHangService {

	@Autowired
	private GioHangRepository gioHangRepo ;
	
	@Autowired
	private GioHangChiTietRepository gioHangChiTietRepo ;
	
	@Autowired
	private GioHangConvertor gioHangConvertor ;
	
	@Autowired
	private GioHangChiTietConvertor gioHangChiTietConvertor ;
	
	@Autowired
	private SanPhamChiTietConvertor sanPhamChiTietConvertor ;
	
	@Autowired
	private SanPhamConvertor sanPhamConvertor ;
	
	@Override
	public GioHangDTO findByKhachHangId(Long id) {
		GioHangDTO gioHangDTO = null  ;
		if(id != null) {
			gioHangDTO = new GioHangDTO();
			List<GioHangChiTietDTO> listGioHangChiTietDTO = new ArrayList<GioHangChiTietDTO>();
			GioHang gioHangEntity = gioHangRepo.findGioHangByKhachHangId(id);
			if(gioHangEntity != null) {
//				List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietRepo.findAllByGioHangId(gioHangEntity.getId());
				for (GioHangChiTiet gioHangChiTiet : gioHangEntity.getGioHangChiTiets()) {
					GioHangChiTietDTO gioHangChiTietDTO = gioHangChiTietConvertor.toDTO(gioHangChiTiet);
					SanPhamChiTietDTO sanPhamChiTietDTO = sanPhamChiTietConvertor.toDTO(gioHangChiTiet.getSanPhamChiTiet());
					sanPhamChiTietDTO.setSanPhamDTO(sanPhamConvertor.toDTO(gioHangChiTiet.getSanPhamChiTiet().getSanPham()));
					gioHangChiTietDTO.setSanPhamChiTietDTO(sanPhamChiTietDTO);
					listGioHangChiTietDTO.add(gioHangChiTietDTO);
				}
				
				gioHangDTO = gioHangConvertor.toDTO(gioHangEntity);
				gioHangDTO.setListGioHangChiTiets(listGioHangChiTietDTO);
				return gioHangDTO ;
			}
		}
		return null ;
	}

	@Override
	public void capNhatTongTien(Long id) {
		GioHang gioHang = gioHangRepo.findGioHangByKhachHangId(id);
		gioHang.setTongTien(gioHangChiTietRepo.tongTien(gioHang.getId()));
		gioHangRepo.save(gioHang);
	}



}
