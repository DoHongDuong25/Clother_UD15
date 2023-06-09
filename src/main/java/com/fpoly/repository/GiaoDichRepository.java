package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.GiaoDich;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich,Long> {

}
