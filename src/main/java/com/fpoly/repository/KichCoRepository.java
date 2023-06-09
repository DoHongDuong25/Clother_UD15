package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KichCo;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Long> {

}
