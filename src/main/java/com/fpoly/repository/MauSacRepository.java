package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.MauSac;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Long> {
	@Query(value = "SELECT * FROM mau_sac c WHERE c.da_xoa = false", nativeQuery = true)
	List<MauSac> selectAllMauSacExist();

}
