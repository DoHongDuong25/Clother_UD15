package com.fpoly.service;
import com.fpoly.dto.HoaDonDTO;
import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface HoaDonService{
    List<HoaDon> getAll();

    Page<HoaDon> getByStatus(int trangThai, Pageable pageable);

    Page<HoaDon> getAll(Pageable pageable);

    List<HoaDon> searchByDate(Date searchDate);

    Integer getMaxId();

	HoaDonDTO findAllByLoaiHoaDonAndMaKhachHang(Integer loaiHoaDon, Long khachHangId ,Pageable pageable);

	void capNhatTrangThaiHuyDon(String maDonHang);

    Page<HoaDon> findHoaDonByTrangThaiAndKhachHangId(int trangThai, Long khachHangId, Pageable pageable);

    List<GiaoDich> timeLine(int trangThai, Long hoaDonId);
}
