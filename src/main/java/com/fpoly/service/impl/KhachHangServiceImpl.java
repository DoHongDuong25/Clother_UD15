package com.fpoly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.convertor.DiaChiConvertor;
import com.fpoly.convertor.KhachHangConvertor;
import com.fpoly.convertor.NguoiDungConvertor;
import com.fpoly.dto.DiaChiDTO;
import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.DiaChi;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService{

	@Autowired
	private KhachHangRepository khachHangRepository ;
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository ;
	
	@Autowired 
	private KhachHangConvertor khachHangConvertor ;
	
	@Autowired
	private DiaChiConvertor diaChiConvertor ;
	
	@Autowired
	private NguoiDungConvertor nguoiDungConvertor ;
	
	@Override
	public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai,Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(trangThai != null) {
			 if(trangThai != 2) {
				 listKhachHang = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai, pageable).getContent();
					for (KhachHang khachHang : listKhachHang) {
						dto = new KhachHangDTO();
						dto = khachHangConvertor.toDTO(khachHang);
						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
						for (DiaChi diaChi : khachHang.getListDiaChi()) {
							diaChiDTO = new DiaChiDTO();
							diaChiDTO = diaChiConvertor.toDTO(diaChi);
							listDiaChiDTO.add(diaChiDTO);
						}
						dto.setListDiaChi(listDiaChiDTO);
						listKhachHangDTO.add(dto);
					}
			 }
		}
		return listKhachHangDTO ;
	}
	
	@Override
	public List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String input, Integer trangThai,
			Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(trangThai != null) {
			 if(trangThai != 2) {
				 listKhachHang = khachHangRepository.
						 findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai,input, pageable).getContent();
					for (KhachHang khachHang : listKhachHang) {
						dto = new KhachHangDTO();
						dto = khachHangConvertor.toDTO(khachHang);
						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
						for (DiaChi diaChi : khachHang.getListDiaChi()) {
							diaChiDTO = new DiaChiDTO();
							diaChiDTO = diaChiConvertor.toDTO(diaChi);
							listDiaChiDTO.add(diaChiDTO);
						}
						dto.setListDiaChi(listDiaChiDTO);
						listKhachHangDTO.add(dto);
					}
			 }
		}
		return listKhachHangDTO ;
	}
	
	
	
	
	

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiThanhDangHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.getOne(id);
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
			nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiThanhKhongHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.getOne(id);
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
			nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public KhachHangDTO findById(Long id) {
		KhachHangDTO dto = new KhachHangDTO();
		KhachHang entity = khachHangRepository.getOne(id);
		if(entity != null) {
			dto = khachHangConvertor.toDTO(entity);
			List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
			for (DiaChi diaChi : entity.getListDiaChi()) {
				DiaChiDTO diaChiDTO = new DiaChiDTO();
				diaChiDTO = diaChiConvertor.toDTO(diaChi);
				listDiaChiDTO.add(diaChiDTO);
			}
			dto.setListDiaChi(listDiaChiDTO);
		}
		return dto;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public KhachHangDTO save(KhachHangDTO dto) {
		KhachHangDTO khachHangDTO = new KhachHangDTO();
		KhachHang khachHangEntity = null ;
		NguoiDung nguoiDungEntity = null ;
		List<KhachHang> listKhachHang = khachHangRepository.findAll();
					try {
						//Cập nhật
						if(dto.getId() != null){
								 khachHangEntity = khachHangRepository.getOne(dto.getId());
								 nguoiDungEntity = new NguoiDung();
								NguoiDung  oldNguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
								if(!dto.getEmail().equalsIgnoreCase(khachHangEntity.getEmail())) {
									for (KhachHang khachHang : listKhachHang) {
										if(khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
											return null ;
										}
									}
								}
									dto.setSoLanMua(khachHangEntity.getSoLanMua());
									khachHangEntity = khachHangConvertor.toEntity(dto);
									nguoiDungEntity = nguoiDungConvertor
											.toEntityByKhachHangDTOAndOldNguoiDungEntity(dto, oldNguoiDungEntity);
									
						}else {
							//Thêm mới
							khachHangEntity = new KhachHang();
							nguoiDungEntity = new NguoiDung();
							for (KhachHang khachHang : listKhachHang) {
								if(khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
									return null ;
								}
							}
							dto.setSoLanMua(0);
							khachHangEntity = khachHangConvertor.toEntity(dto);
							nguoiDungEntity = nguoiDungConvertor.toEntityByKhachHangDTO(dto);
						}
						khachHangEntity = khachHangRepository.save(khachHangEntity);
						nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);
						khachHangDTO = khachHangConvertor.toDTO(khachHangEntity);
						return khachHangDTO;
					}catch(Exception e) {
						e.printStackTrace();
					}
					return khachHangDTO ;
		}

	@Override
	public List<KhachHangDTO> findAll(Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		
 		listKhachHang = khachHangRepository.findAll(pageable).getContent();
		 for (KhachHang khachHang : listKhachHang) {
			    dto = new KhachHangDTO();
			    dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
				for (DiaChi diaChi : khachHang.getListDiaChi()) {
					diaChiDTO = new DiaChiDTO();
					diaChiDTO  = diaChiConvertor.toDTO(diaChi);
					listDiaChiDTO.add(diaChiDTO);
				}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
		 }
		 
		 return listKhachHangDTO ;
	}



	@Override
	public List<KhachHangDTO> findAll() {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		
		listKhachHang = khachHangRepository.findAll();
		 for (KhachHang khachHang : listKhachHang) {
			    dto = new KhachHangDTO();
			    dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
				for (DiaChi diaChi : khachHang.getListDiaChi()) {
					diaChiDTO = new DiaChiDTO();
					diaChiDTO  = diaChiConvertor.toDTO(diaChi);
					listDiaChiDTO.add(diaChiDTO);
				}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
		 }
		 return listKhachHangDTO ;
	}



	@Override
	public List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai,Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(soDienThoai != null) {
			 listKhachHang = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable).getContent();
			for (KhachHang khachHang : listKhachHang) {
				dto = new KhachHangDTO();
				dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
					for (DiaChi diaChi : khachHang.getListDiaChi()) {
						diaChiDTO = new DiaChiDTO();
						diaChiDTO = diaChiConvertor.toDTO(diaChi);
						listDiaChiDTO.add(diaChiDTO);
					}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
				}
		}
		return listKhachHangDTO ;
	}



	@Override
	public int countAll() {
		return (int) khachHangRepository.count();
	}



	@Override
	public int countByTrangThai(Integer trangThai) {
		return khachHangRepository.countByTrangThai(trangThai);
	}



	@Override
	public int countByInput(String input) {
		return khachHangRepository.countByInput(input);
	}

	@Override
	public int countByInputVaTrangThai(String input, Integer trangThai) {
		return khachHangRepository.countByInputVaTrangThai(input,trangThai);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiTheoId(Long id) {
		KhachHang entity = khachHangRepository.getOne(id);
		NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(entity.getEmail());
		if(entity != null) {
			if(entity.getTrangThai() == 1) {
				nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
				khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(entity.getId());
			}
			if(entity.getTrangThai() == 0) {
				nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
				khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(entity.getId());
			}
		}
	}

	
	
}
