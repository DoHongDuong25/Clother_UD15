package com.fpoly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.dto.DiaChiDTO;
import com.fpoly.entity.DiaChi;
import com.fpoly.repository.DiaChiRepository;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.service.DiaChiService;


@Service
public class DiaChiServiceImpl implements DiaChiService {
	
	@Autowired
	private DiaChiRepository diaChiRepository;
	
	@Autowired
	private KhachHangRepository khachHangRepository ;
	@Override
	@Transactional
	public DiaChiDTO save(DiaChiDTO result) {
			DiaChiDTO dto = new DiaChiDTO();
			DiaChi diaChi = new DiaChi();
			diaChi.setDiaChi(result.getCity()+"-"+result.getDistrict()+"-"+result.getWard()+"-"+result.getSoNha());
			diaChi.setKhachHang(khachHangRepository.getOne(result.getKhachHangId()));
				diaChiRepository.save(diaChi);
				dto.setDiaChi(diaChi.getDiaChi());
				dto.setCity(result.getCity());
				dto.setDistrict(result.getDistrict());
				dto.setWard(result.getWard());
				dto.setSoNha(result.getSoNha());
				dto.setKhachHangId(result.getKhachHangId());
		return dto;
	}
	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			diaChiRepository.deleteById(id);
		}
	}
	@Override
	public int countByMaKhachHang(Long id ) {
		return diaChiRepository.countByMaKhachHang(id);
	}
	@SuppressWarnings("null")
	@Override
	public List<DiaChiDTO> findAllDiaChiByMaKhachHang(Long id, Pageable pageale) {
		List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
		List<DiaChi> listDiaChiEntity = new ArrayList<DiaChi>();
		DiaChiDTO diaChiDTO = null;
		listDiaChiEntity = diaChiRepository.findAllByMaKhachHang(id,pageale).getContent();
		for (DiaChi diaChi : listDiaChiEntity) {
			diaChiDTO = new DiaChiDTO();
			diaChiDTO.setId(diaChi.getId());
			diaChiDTO.setDiaChi(diaChi.getDiaChi());
			listDiaChiDTO.add(diaChiDTO);
		}
		return listDiaChiDTO;
	}

}
