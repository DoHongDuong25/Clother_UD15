package com.fpoly.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fpoly.entity.NguoiDung;
import com.fpoly.entity.NguoiDungVaiTro;

@SuppressWarnings("serial")
public class NguoiDungDetails implements UserDetails {
	
	private NguoiDung nguoiDung ;
	
	public NguoiDungDetails(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<NguoiDungVaiTro> nguoiDungVaiTros = nguoiDung.getListNguoiDungVaiTro();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (NguoiDungVaiTro nguoiDungVaiTro : nguoiDungVaiTros) {
			authorities.add(new SimpleGrantedAuthority(nguoiDungVaiTro.getVaiTro().getCode()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return nguoiDung.getMatKhau();
	}

	@Override
	public String getUsername() {
		return nguoiDung.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getTenNguoiDung() {
		return nguoiDung.getTenNguoiDung();
	}
	
	
}
