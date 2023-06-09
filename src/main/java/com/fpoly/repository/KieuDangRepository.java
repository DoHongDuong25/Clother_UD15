package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KieuDang;

@Repository
public interface KieuDangRepository extends JpaRepository<KieuDang,Long> {

}
