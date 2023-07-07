package com.fpoly.service.impl;

import com.fpoly.convertor.HoaDonConvertor;
import com.fpoly.dto.HoaDonDTO;
import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
	
	@Autowired
	private HoaDonConvertor hoaDonConvertor ;
	
    private final HoaDonRepository hoaDonRepository;

    private HoaDonRepoditory2 hoaDonRepository2;
    private JdbcTemplate jdbcTemplate;

    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Override
    public List<HoaDon> getAll() {
        return (List<HoaDon>) hoaDonRepository.findAll();
    }

    @Override
    public Page<HoaDon> getByStatus(int trangThai, Pageable pageable) {
        return hoaDonRepository2.findByTrangThaiHoaDonListTrangThai(trangThai, pageable);
    }

    @Override
    public Page<HoaDon> getAll(Pageable pageable) {
        return hoaDonRepository2.findAll(pageable);
    }

    @Override
    public List<HoaDon> searchByDate(Date searchDate) {
        return hoaDonRepository.findByNgayTao(searchDate);
    }

    @Override
    public Integer getMaxId() {
        return hoaDonRepository.getMaxId();
    }

	@Override
	public HoaDonDTO findAllByLoaiHoaDonAndMaKhachHang(Integer loaiHoaDon,Long khachHangId, Pageable pageable) {
		List<HoaDonDTO> listHoaDonDTO = new ArrayList<HoaDonDTO>();
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		HoaDonDTO hoaDonDTO = new HoaDonDTO() ;
		hoaDonDTO.setPage(pageable.getPageNumber()+1);
		hoaDonDTO.setTotalItems(hoaDonRepository.countByLoaiHoaDon(0));
		hoaDonDTO.setTotalPages((int) Math.ceil((double) hoaDonDTO.getTotalItems() / pageable.getPageSize()));
		listHoaDon = hoaDonRepository.findAllByLoaiHoaDonAndMaKhachHang(loaiHoaDon,khachHangId,pageable).getContent();
		for (HoaDon hoaDon : listHoaDon) {
			HoaDonDTO result = new HoaDonDTO();
			result = hoaDonConvertor.toDTO(hoaDon);
			listHoaDonDTO.add(result);
		}
		hoaDonDTO.setListHoaDonDTO(listHoaDonDTO);
		return hoaDonDTO;
	}

	@Override
	@Transactional
	public void capNhatTrangThaiHuyDon(String maDonHang) {
			HoaDon hoaDon = hoaDonRepository.findByMaDonHang(maDonHang);
			if(hoaDon != null) {
				hoaDonRepository.capNhatTrangThaiThanhHuyDon(hoaDon.getId());
			}
	}
}
