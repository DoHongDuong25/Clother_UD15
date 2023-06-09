package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.PhongCach;

@Repository
public interface PhongCachRepository extends JpaRepository<PhongCach, Long> {

}
