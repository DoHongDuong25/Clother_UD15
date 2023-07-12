package com.fpoly.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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


@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private VaiTroRepository vaiTroRepository;
	
	@Autowired
	private NguoiDungVaiTroRepository nguoiDungVaiTroRepository;
	
	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private GioHangRepository gioHangRepository ;
	@Bean
	public PasswordEncoder passwordEncorder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// vai trof
		if (vaiTroRepository.findByTenVaiTro("ADMIN") == null) {
			VaiTro vaiTro = new VaiTro();
			vaiTro.setName("Quản trị viên");
			vaiTro.setCode("ADMIN");
			vaiTroRepository.save(vaiTro);
		}

		if (vaiTroRepository.findByTenVaiTro("STAFF") == null) {
			VaiTro vaiTro = new VaiTro();
			vaiTro.setName("Nhân viên");
			vaiTro.setCode("STAFF");
			vaiTroRepository.save(vaiTro);
		}
		
		if (vaiTroRepository.findByTenVaiTro("CUSTOMER") == null) {
			VaiTro vaiTro = new VaiTro();
			vaiTro.setName("Khách hàng");
			vaiTro.setCode("CUSTOMER");
			vaiTroRepository.save(vaiTro);
		}

		// Admin account
		if (nguoiDungRepository.findByEmail("admin@gmail.com") == null) {
			NguoiDung admin = new NguoiDung();
			admin.setEmail("admin@gmail.com");
			admin.setMatKhau("123456");
			admin.setTenNguoiDung("Nguyễn Văn Đương");
			admin.setSoDienThoai("0965221785");
			admin.setTrangThai(0);
			admin.setDaXoa(false);
			admin.setMaNguoiDung("NGUOIDUNG01");
			nguoiDungRepository.save(admin);
			List<NguoiDungVaiTro> listNguoiDungVaiTro = new ArrayList<>();
			NguoiDungVaiTro nguoiDungVaiTroAdmin = new NguoiDungVaiTro();
			nguoiDungVaiTroAdmin.setNguoiDung(nguoiDungRepository.findByEmail("admin@gmail.com"));
			nguoiDungVaiTroAdmin.setVaiTro(vaiTroRepository.findByTenVaiTro("ADMIN"));
			NguoiDungVaiTro nguoiDungVaiTroSTAFF = new NguoiDungVaiTro();
			nguoiDungVaiTroSTAFF.setNguoiDung(nguoiDungRepository.findByEmail("admin@gmail.com"));
			nguoiDungVaiTroSTAFF.setVaiTro(vaiTroRepository.findByTenVaiTro("STAFF"));
			NguoiDungVaiTro nguoiDungVaiTroCUSTOMER = new NguoiDungVaiTro();
			nguoiDungVaiTroCUSTOMER.setNguoiDung(nguoiDungRepository.findByEmail("admin@gmail.com"));
			nguoiDungVaiTroCUSTOMER.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
			listNguoiDungVaiTro.add(nguoiDungVaiTroAdmin);
			listNguoiDungVaiTro.add(nguoiDungVaiTroSTAFF);
			listNguoiDungVaiTro.add(nguoiDungVaiTroCUSTOMER);
			for (NguoiDungVaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
				nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
			}
		}
		if (khachHangRepository.findByEmail("admin@gmail.com") == null) {
			KhachHang admin = new KhachHang();
			admin.setEmail("admin@gmail.com");
			admin.setMatKhau("123456");
			admin.setHoTen("Nguyễn Văn Đương");
			admin.setSoDienThoai("0965221785");
			admin.setTrangThai(1);
			admin.setSoLanMua(0);
			admin = khachHangRepository.save(admin);
			GioHang gioHang = new GioHang(null, null, admin, 1, 0, null);
			gioHangRepository.save(gioHang);
		}

		// Member account
		if (nguoiDungRepository.findByEmail("staff@gmail.com") == null) {
			NguoiDung staff = new NguoiDung();
			staff.setEmail("staff@gmail.com");
			staff.setMatKhau("123456");
			staff.setTenNguoiDung("Nguyễn Văn Đương");
			staff.setSoDienThoai("0965221785");
			staff.setTrangThai(0);
			staff.setDaXoa(false);
			staff.setMaNguoiDung("NGUOIDUNG02");
			nguoiDungRepository.save(staff);
			List<NguoiDungVaiTro> listNguoiDungVaiTro = new ArrayList<>();
			NguoiDungVaiTro nguoiDungVaiTroSTAFF = new NguoiDungVaiTro();
			nguoiDungVaiTroSTAFF.setNguoiDung(nguoiDungRepository.findByEmail("staff@gmail.com"));
			nguoiDungVaiTroSTAFF.setVaiTro(vaiTroRepository.findByTenVaiTro("STAFF"));
			NguoiDungVaiTro nguoiDungVaiTroCUSTOMER = new NguoiDungVaiTro();
			nguoiDungVaiTroCUSTOMER.setNguoiDung(nguoiDungRepository.findByEmail("staff@gmail.com"));
			nguoiDungVaiTroCUSTOMER.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
			listNguoiDungVaiTro.add(nguoiDungVaiTroSTAFF);
			listNguoiDungVaiTro.add(nguoiDungVaiTroCUSTOMER);
			for (NguoiDungVaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
				nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
			}
		}
		if (khachHangRepository.findByEmail("staff@gmail.com") == null) {
			KhachHang staff = new KhachHang();
			staff.setEmail("staff@gmail.com");
			staff.setMatKhau("123456");
			staff.setHoTen("Nguyễn Văn Đương");
			staff.setSoDienThoai("0965221785");
			staff.setTrangThai(1);
			staff.setSoLanMua(0);
			staff = khachHangRepository.save(staff);
			GioHang gioHang = new GioHang(null, null, staff, 1, 0, null);
			gioHangRepository.save(gioHang);
		}
		// Shipper account
		if (nguoiDungRepository.findByEmail("customer@gmail.com") == null) {
			NguoiDung member = new NguoiDung();
			member.setEmail("customer@gmail.com");
			member.setMatKhau("123456");
			member.setTenNguoiDung("Nguyễn Văn Đương");
			member.setSoDienThoai("0965221785");
			member.setTrangThai(0);
			member.setDaXoa(false);
			member.setMaNguoiDung("NGUOIDUNG03");
			nguoiDungRepository.save(member);
			List<NguoiDungVaiTro> listNguoiDungVaiTro = new ArrayList<>();
			NguoiDungVaiTro nguoiDungVaiTroCUSTOMER = new NguoiDungVaiTro();
			nguoiDungVaiTroCUSTOMER.setNguoiDung(nguoiDungRepository.findByEmail("customer@gmail.com"));
			nguoiDungVaiTroCUSTOMER.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
			listNguoiDungVaiTro.add(nguoiDungVaiTroCUSTOMER);
			for (NguoiDungVaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
				nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
			}
		}
		if (khachHangRepository.findByEmail("customer@gmail.com") == null) {
			KhachHang customer = new KhachHang();
			customer.setEmail("customer@gmail.com");
			customer.setMatKhau("123456");
			customer.setHoTen("Nguyễn Văn Đương");
			customer.setSoDienThoai("0965221785");
			customer.setTrangThai(1);
			customer.setSoLanMua(0);
			customer = khachHangRepository.save(customer);
			GioHang gioHang = new GioHang(null, null, customer, 1, 0, null);
			gioHangRepository.save(gioHang);
		}
	}
}

