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
import com.fpoly.entity.GioHang;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.NguoiDung;
import com.fpoly.entity.NguoiDungVaiTro;
import com.fpoly.entity.VaiTro;
import com.fpoly.repository.GioHangRepository;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.repository.NguoiDungVaiTroRepository;
import com.fpoly.repository.VaiTroRepository;
import com.fpoly.service.KhachHangService;
import com.fpoly.service.MailService;
import com.fpoly.util.RanDomUtil;

@Service
public class KhachHangServiceImpl implements KhachHangService {

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private KhachHangConvertor khachHangConvertor;

	@Autowired
	private DiaChiConvertor diaChiConvertor;

	@Autowired
	private NguoiDungConvertor nguoiDungConvertor;
	
	@Autowired
	private GioHangRepository gioHangRepository ;
	
	@Autowired
	private VaiTroRepository vaiTroRepository ;
	
	@Autowired
	private NguoiDungVaiTroRepository nguoiDungVaiTroRepository ;

	@Autowired
	private MailService mailService ;
	
	
	@Override
	public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai, Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null;
		DiaChiDTO diaChiDTO = null;
		if (trangThai != null) {
			if (trangThai != 2) {
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
					dto.setListDiaChiDTO(listDiaChiDTO);
					listKhachHangDTO.add(dto);
				}
			}
		}
		return listKhachHangDTO;
	}

	@Override
	public List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String input, Integer trangThai, Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null;
		DiaChiDTO diaChiDTO = null;
		if (trangThai != null) {
			if (trangThai != 2) {
				listKhachHang = khachHangRepository
						.findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai, input, pageable).getContent();
				for (KhachHang khachHang : listKhachHang) {
					dto = new KhachHangDTO();
					dto = khachHangConvertor.toDTO(khachHang);
					List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
					for (DiaChi diaChi : khachHang.getListDiaChi()) {
						diaChiDTO = new DiaChiDTO();
						diaChiDTO = diaChiConvertor.toDTO(diaChi);
						listDiaChiDTO.add(diaChiDTO);
					}
					dto.setListDiaChiDTO(listDiaChiDTO);
					listKhachHangDTO.add(dto);
				}
			}
		}
		return listKhachHangDTO;
	}

	@Override
	@Transactional
	public void capNhatTrangThaiThanhDangHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.findById(id).get();
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
			if(nguoiDungEntity != null) {
				nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
			}
			
		}
	}

	@Override
	@Transactional
	public void capNhatTrangThaiThanhKhongHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.findById(id).get();
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
			if(nguoiDungEntity != null) {
				nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
			}
			
		}
	}

	@Override
	public KhachHangDTO findById(Long id) {
		KhachHangDTO dto = null;
		KhachHang entity = khachHangRepository.findById(id).get();
		if (entity != null) {
			dto = new KhachHangDTO();
			dto = khachHangConvertor.toDTO(entity);
			List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
			for (DiaChi diaChi : entity.getListDiaChi()) {
				DiaChiDTO diaChiDTO = new DiaChiDTO();
				diaChiDTO = diaChiConvertor.toDTO(diaChi);
				listDiaChiDTO.add(diaChiDTO);
			}
			dto.setListDiaChiDTO(listDiaChiDTO);
		}
		return dto;
	}

	@Override
	@Transactional
	public KhachHangDTO save(KhachHangDTO dto) {
		KhachHangDTO khachHangDTO = new KhachHangDTO();
		KhachHang khachHangEntity = null;
		NguoiDung nguoiDungEntity = null;
		List<KhachHang> listKhachHang = khachHangRepository.findAll();
		try {
			// Cập nhật
			if (dto.getId() != null) {
				khachHangEntity = khachHangRepository.findById(dto.getId()).get();
				nguoiDungEntity = new NguoiDung();
				NguoiDung oldNguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
				if (!dto.getEmail().equalsIgnoreCase(khachHangEntity.getEmail())) {
					for (KhachHang khachHang : listKhachHang) {
						if (khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
							return null;
						}
					}
				}
				dto.setSoLanMua(khachHangEntity.getSoLanMua());
				khachHangEntity = khachHangConvertor.toEntity(dto);
				nguoiDungEntity = nguoiDungConvertor.toEntityByKhachHangDTOAndOldNguoiDungEntity(dto,
						oldNguoiDungEntity);
				nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);

			} else {
				// Thêm mới
				khachHangEntity = new KhachHang();
				nguoiDungEntity = new NguoiDung();
				for (KhachHang khachHang : listKhachHang) {
					if (khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
						return null;
					}
				}
				dto.setSoLanMua(0);
				khachHangEntity = khachHangConvertor.toEntity(dto);
				nguoiDungEntity = nguoiDungConvertor.toEntityByKhachHangDTO(dto);
				nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);
				NguoiDungVaiTro nguoiDungVaiTro = new NguoiDungVaiTro();
				nguoiDungVaiTro.setNguoiDung(nguoiDungEntity);
				nguoiDungVaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
				nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
				GioHang gioHang = new GioHang(null, null, khachHangEntity, 1, 0, null);
				gioHangRepository.save(gioHang);
			}
			khachHangEntity = khachHangRepository.save(khachHangEntity);
			khachHangDTO = khachHangConvertor.toDTO(khachHangEntity);
			return khachHangDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return khachHangDTO;
	}

	@Override
	public List<KhachHangDTO> findAll(Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null;
		DiaChiDTO diaChiDTO = null;

		listKhachHang = khachHangRepository.findAll(pageable).getContent();
		for (KhachHang khachHang : listKhachHang) {
			dto = new KhachHangDTO();
			dto = khachHangConvertor.toDTO(khachHang);
			List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
			for (DiaChi diaChi : khachHang.getListDiaChi()) {
				diaChiDTO = new DiaChiDTO();
				diaChiDTO = diaChiConvertor.toDTO(diaChi);
				listDiaChiDTO.add(diaChiDTO);
			}
			dto.setListDiaChiDTO(listDiaChiDTO);
			listKhachHangDTO.add(dto);
		}

		return listKhachHangDTO;
	}

	@Override
	public List<KhachHangDTO> findAll() {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null;
		DiaChiDTO diaChiDTO = null;

		listKhachHang = khachHangRepository.findAll();
		for (KhachHang khachHang : listKhachHang) {
			dto = new KhachHangDTO();
			dto = khachHangConvertor.toDTO(khachHang);
			List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
			for (DiaChi diaChi : khachHang.getListDiaChi()) {
				diaChiDTO = new DiaChiDTO();
				diaChiDTO = diaChiConvertor.toDTO(diaChi);
				listDiaChiDTO.add(diaChiDTO);
			}
			dto.setListDiaChiDTO(listDiaChiDTO);
			listKhachHangDTO.add(dto);
		}
		return listKhachHangDTO;
	}

	@Override
	public List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai, Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null;
		DiaChiDTO diaChiDTO = null;
		if (soDienThoai != null) {
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
				dto.setListDiaChiDTO(listDiaChiDTO);
				listKhachHangDTO.add(dto);
			}
		}
		return listKhachHangDTO;
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
		return khachHangRepository.countByInputVaTrangThai(input, trangThai);
	}

	@Override
	@Transactional
	public void capNhatTrangThaiTheoId(Long id) {
		KhachHang entity = khachHangRepository.findById(id).get();
		NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(entity.getEmail());
		if (entity != null) {
			if (entity.getTrangThai() == 1) {
				if(nguoiDungEntity != null) {
					nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
				}
				khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(entity.getId());
			}
			if (entity.getTrangThai() == 0) {
				if(nguoiDungEntity != null) {
					nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
				}
				khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(entity.getId());
			}
		}
	}

	@Override
	@Transactional
	public void updateUserStatus(Long id, int trangThai) {
		KhachHang user = khachHangRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));
		user.setTrangThai(trangThai);
		user = khachHangRepository.save(user);
		NguoiDung nguoiDung = nguoiDungRepository.findByEmail(user.getEmail());
		if(nguoiDung != null) {
			if(user.getTrangThai() == 1) {
				nguoiDungRepository
				.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDung.getId());
			}
			if(user.getTrangThai() == 0) {
				nguoiDungRepository
				.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDung.getId());
			}
		}else {
			return ;
		}
	}

	@Override
	public KhachHangDTO findByEmail(String auth) {
		KhachHangDTO khachHangDT0 = new KhachHangDTO();
			KhachHang khachHang = khachHangRepository.findByEmail(auth);
			if(khachHang != null) {
				 khachHangDT0 = khachHangConvertor.toDTO(khachHang);
				 return khachHangDT0 ;
			}
		return null;
	}

	@Override
	public KhachHangDTO findByEmailAndTrangThai(String email, int trangThai) {
		KhachHang khachHang = khachHangRepository.findByEmailAndTrangThai(email,trangThai) ;
		if(khachHang != null) {
			return khachHangConvertor.toDTO(khachHang);
		}
		return null ;
	}

	@Override
	@Transactional
	public KhachHangDTO register(KhachHangDTO khachHangDTO) {
		char[] password = RanDomUtil.randomFull();
		KhachHang khachHang = new KhachHang();
		khachHangDTO.setSoLanMua(0);
		khachHangDTO.setTrangThai(1);
		khachHangDTO.setMatKhau(new String(password));
		khachHang = khachHangConvertor.toEntity(khachHangDTO);
		mailService.sendMail("duongnvph17448@fpt.edu.vn",
				khachHangDTO.getEmail(), 
											"Bạn đã đăng ký tài khoản thành công !", 
											"Họ tên  : "+khachHangDTO.getHoTen() +"\n" +
							        		 		 "Số điện thoại  :"+khachHangDTO.getSoDienThoai()
							        		 		 +"Mật khẩu : " + new String(password));
		NguoiDung nguoiDung = nguoiDungConvertor.toEntityByKhachHangDTO(khachHangDTO);
		NguoiDungVaiTro nguoiDungVaiTro = new NguoiDungVaiTro();
		nguoiDungVaiTro.setNguoiDung(nguoiDung);
		nguoiDungVaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
		nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
		nguoiDungRepository.save(nguoiDung);
		khachHang = khachHangRepository.save(khachHang);
		GioHang gioHang = new GioHang();
		gioHang.setTongTien(0);
		gioHang.setTrangThai(1);
		gioHang.setKhachHang(khachHang);
		gioHangRepository.save(gioHang);
		return khachHangConvertor.toDTO(khachHang);
	}

	@Override
	public String sendCode(String email) {
		String code = "";
		KhachHang entity = khachHangRepository.findByEmail(email);
		code = new String(RanDomUtil.randomNumber());
		if(entity.getEmail() != null) {
			mailService.sendMail("duongnvph17448@fpt.edu.vn",
					entity.getEmail(), 
					"Your code reset password", 
	        		 "Code : " + code);
		}
		return code;
	}

	@Override
	public void updatePassword(String email,KhachHangDTO khachHangDTO) {
		KhachHang khachHang = khachHangRepository.findByEmailAndTrangThai(email, 1);
		NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
		if(nguoiDung != null) {
			nguoiDung.setMatKhau(khachHangDTO.getMatKhau());
		}
		if(khachHang != null) {
			khachHang.setMatKhau(khachHangDTO.getMatKhau());
		}
		nguoiDungRepository.save(nguoiDung);
		khachHangRepository.save(khachHang);
	}

//	@Autowired
//	private KhachHangRepository khachHangRepository ;
//	
//	@Override
//	public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai,Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			 if(trangThai != 2) {
//				 listKhachHang = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai, pageable).getContent();
//					for (KhachHang khachHang : listKhachHang) {
//						dto = new KhachHangDTO();
//						dto.setId(khachHang.getId());
//						dto.setEmail(khachHang.getEmail());
//						dto.setMatKhau(khachHang.getMatKhau());
//						dto.setHoTen(khachHang.getHoTen());
//						dto.setSoDienThoai(khachHang.getSoDienThoai());
//						dto.setTrangThai(khachHang.getTrangThai());
//						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//						for (DiaChi diaChi : khachHang.getListDiaChi()) {
//							diaChiDTO = new DiaChiDTO();
//							diaChiDTO.setId(diaChi.getId());
//							diaChiDTO.setDiaChi(diaChi.getDiaChi());
//							listDiaChiDTO.add(diaChiDTO);
//						}
//						dto.setListDiaChi(listDiaChiDTO);
//						listKhachHangDTO.add(dto);
//					}
//			 }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	@Override
//	public List<KhachHangDTO> findAllBySoDienThoaiVaTrangThaiCoPhanTrang(String soDienThoai, Integer trangThai,
//			Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			 if(trangThai != 2) {
//				 listKhachHang = khachHangRepository.findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai,soDienThoai, pageable).getContent();
//					for (KhachHang khachHang : listKhachHang) {
//						dto = new KhachHangDTO();
//						dto.setId(khachHang.getId());
//						dto.setEmail(khachHang.getEmail());
//						dto.setMatKhau(khachHang.getMatKhau());
//						dto.setHoTen(khachHang.getHoTen());
//						dto.setSoDienThoai(khachHang.getSoDienThoai());
//						dto.setTrangThai(khachHang.getTrangThai());
//						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//						for (DiaChi diaChi : khachHang.getListDiaChi()) {
//							diaChiDTO = new DiaChiDTO();
//							diaChiDTO.setId(diaChi.getId());
//							diaChiDTO.setDiaChi(diaChi.getDiaChi());
//							listDiaChiDTO.add(diaChiDTO);
//						}
//						dto.setListDiaChi(listDiaChiDTO);
//						listKhachHangDTO.add(dto);
//					}
//			 }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	
//	
//	@Override
//	public List<KhachHangDTO> locDanhSachTheoTrangThai(Integer trangThai) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			if(trangThai != 2) {
//			 listKhachHang = khachHangRepository.findAllByTrangThaiKhongCoPhanTrang(trangThai);
//			for (KhachHang khachHang : listKhachHang) {
//				dto = new KhachHangDTO();
//				dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//					for (DiaChi diaChi : khachHang.getListDiaChi()) {
//						diaChiDTO = new DiaChiDTO();
//						diaChiDTO.setId(diaChi.getId());
//						diaChiDTO.setDiaChi(diaChi.getDiaChi());
//						listDiaChiDTO.add(diaChiDTO);
//					}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//				}
//		    }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	
//
//	@Override
//	@Transactional
//	public void capNhatTrangThaiThanhDangHoatDongTheoMa(long[] ids) {
//		for (long id : ids) {
//			khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
//		}
//	}
//
//	@Override
//	@Transactional
//	public void capNhatTrangThaiThanhKhongHoatDongTheoMa(long[] ids) {
//		for (long id : ids) {
//			khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
//		}
//	}
//
//	@Override
//	public KhachHangDTO findById(Long id) {
//		KhachHangDTO dto = new KhachHangDTO();
//		KhachHang entity = khachHangRepository.getOne(id);
//		if(entity != null) {
//			dto.setId(entity.getId());
//			dto.setEmail(entity.getEmail());
//			dto.setHoTen(entity.getHoTen());
//			dto.setMatKhau(entity.getMatKhau());
//			dto.setSoDienThoai(entity.getSoDienThoai());
//			List<DiaChiDTO> listDiaChidTO = new ArrayList<DiaChiDTO>();
//			for (DiaChi diaChi : entity.getListDiaChi()) {
//				DiaChiDTO dto1 = new DiaChiDTO();
//				dto1.setId(diaChi.getId());
//				dto1.setDiaChi(diaChi.getDiaChi());
//				listDiaChidTO.add(dto1);
//			}
//			dto.setListDiaChi(listDiaChidTO);
//		}
//		return dto;
//	}
//
//	@Override
//	public KhachHangDTO save(KhachHangDTO dto) {
//		KhachHangDTO khachHangDTO = new KhachHangDTO();
//		KhachHang khachHangEntity = new KhachHang();
////		List<KhachHang> list = khachHangRepository.findAll();
////		for (KhachHang khachHang : list) {
////			if (!khachHang.getEmail().equals(dto.getEmail())) {
//					if(dto.getId() != null){
//						KhachHang khachHangTonTai = khachHangRepository.findByEmail(dto.getEmail());
//						khachHangTonTai.setId(dto.getId());
//						khachHangTonTai.setEmail(dto.getEmail());
//						khachHangTonTai.setHoTen(dto.getHoTen());
//						khachHangTonTai.setMatKhau(dto.getMatKhau());
//						khachHangTonTai.setSoDienThoai(dto.getSoDienThoai());
//						khachHangTonTai.setTrangThai(dto.getTrangThai());
//						khachHangEntity = khachHangTonTai ;
//					}else {
//						KhachHang khachHangMoi = new KhachHang();
//						khachHangMoi.setEmail(dto.getEmail());
//						khachHangMoi.setHoTen(dto.getHoTen());
//						khachHangMoi.setMatKhau(dto.getMatKhau());
//						khachHangMoi.setSoDienThoai(dto.getSoDienThoai());
//						khachHangMoi.setTrangThai(1);
//						khachHangEntity = khachHangMoi ;
//					}
//					khachHangEntity = khachHangRepository.save(khachHangEntity);
//					khachHangDTO.setId(khachHangEntity.getId());
//					khachHangDTO.setEmail(khachHangEntity.getEmail());
//					khachHangDTO.setHoTen(khachHangEntity.getHoTen());
//					khachHangDTO.setMatKhau(khachHangEntity.getMatKhau());
//					khachHangDTO.setSoDienThoai(khachHangEntity.getSoDienThoai());
////				}else {
////					 new SQLIntegrityConstraintViolationException();
////				}
//					return khachHangDTO;
//				
//		}
//
//	@Override
//	public List<KhachHangDTO> findAll(Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		
// 		listKhachHang = khachHangRepository.findAll(pageable).getContent();
//		 for (KhachHang khachHang : listKhachHang) {
//			    dto = new KhachHangDTO();
//			    dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//				for (DiaChi diaChi : khachHang.getListDiaChi()) {
//					diaChiDTO = new DiaChiDTO();
//					diaChiDTO.setId(diaChi.getId());
//					diaChiDTO.setDiaChi(diaChi.getDiaChi());
//					listDiaChiDTO.add(diaChiDTO);
//				}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//		 }
//		 
//		 return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public List<KhachHangDTO> findAll() {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		
//		listKhachHang = khachHangRepository.findAll();
//		 for (KhachHang khachHang : listKhachHang) {
//			    dto = new KhachHangDTO();
//			    dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//				for (DiaChi diaChi : khachHang.getListDiaChi()) {
//					diaChiDTO = new DiaChiDTO();
//					diaChiDTO.setId(diaChi.getId());
//					diaChiDTO.setDiaChi(diaChi.getDiaChi());
//					listDiaChiDTO.add(diaChiDTO);
//				}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//		 }
//		 return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public List<KhachHangDTO> findAllBySoDienThoaiCoPhanTrang(String soDienThoai,Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(soDienThoai != null) {
//			 listKhachHang = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable).getContent();
//			for (KhachHang khachHang : listKhachHang) {
//				dto = new KhachHangDTO();
//				dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//					for (DiaChi diaChi : khachHang.getListDiaChi()) {
//						diaChiDTO = new DiaChiDTO();
//						diaChiDTO.setId(diaChi.getId());
//						diaChiDTO.setDiaChi(diaChi.getDiaChi());
//						listDiaChiDTO.add(diaChiDTO);
//					}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//				}
//		}
//		return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public int countAll() {
//		return (int) khachHangRepository.count();
//	}
//
//
//
//	@Override
//	public int countByTrangThai(Integer trangThai) {
//		return khachHangRepository.countByTrangThai(trangThai);
//	}
//
//
//
//	@Override
//	public int countBySoDienThoai(String soDienThoai) {
//		return khachHangRepository.countBySoDienThoai(soDienThoai);
//	}
//
//	@Override
//	public int countBySoDienThoaiVaTrangThai(String soDienThoai, Integer trangThai) {
//		return khachHangRepository.countBySoDienThoaiVaTrangThai(soDienThoai,trangThai);
//	}
}
