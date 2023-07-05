package com.fpoly.service.impl;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.convertor.GioHangChiTietConvertor;
import com.fpoly.dto.GioHangChiTietDTO;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.repository.GioHangChiTietRepository;
import com.fpoly.service.GioHangChiTietService;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

	@Autowired
	private GioHangChiTietRepository gioHangChiTietRepo ;
	
	@Autowired
	private GioHangChiTietConvertor gioHangChiTietConvertor ;
	
	@Override
	public List<GioHangChiTietDTO> findAllByGioHangId(Long id) {
		List<GioHangChiTietDTO> listGioHangChiTietDTO = new ArrayList<GioHangChiTietDTO>();
		List<GioHangChiTiet> listGioHangChiTiet = new ArrayList<GioHangChiTiet>();
		GioHangChiTietDTO gioHangChiTietDTO = null ;
		try {
			listGioHangChiTiet = gioHangChiTietRepo.findAllByGioHangId(id);
			for (GioHangChiTiet gioHangChiTiet : listGioHangChiTiet) {
				gioHangChiTietDTO = new GioHangChiTietDTO();
				gioHangChiTietDTO = gioHangChiTietConvertor.toDTO(gioHangChiTiet);
				listGioHangChiTietDTO.add(gioHangChiTietDTO);
			}
			return listGioHangChiTietDTO;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listGioHangChiTietDTO;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatSoLuongGioHangChiTiet(Long[] ids, Integer[] soLuongs) {
		List<Integer> listSoLuong = toInteger(soLuongs);
		for (int i = 0; i < ids.length; i++) {
			Long id =  (Long)Array.get(ids, i);
			Integer soLuong = listSoLuong.get((int) i);
			 if(soLuong > 0) {
				 GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.getOne(id);
				 gioHangChiTiet.setSoLuong(soLuong);
				 gioHangChiTiet.setThanhTien(BigDecimal.valueOf(gioHangChiTiet.getSoLuong()*gioHangChiTiet.getDonGia()));
				 gioHangChiTietRepo.save(gioHangChiTiet);
			 }
		}
	}
	public List<Integer> toInteger(Integer[] integers) {
		List<Integer> list = new ArrayList<>() ;
		for (int i = 0; i < integers.length; i++) {
			Integer integer = integers[i];
			list.add(integer);
		}
		return list ;
	}

}
