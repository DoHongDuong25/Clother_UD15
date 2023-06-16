package com.fpoly.service.impl;

import com.fpoly.entity.KhuyenMai;
import com.fpoly.repository.KhuyenMaiRepository;
import com.fpoly.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KhuyenMaiServiceImp implements KhuyenMaiService{
    private final KhuyenMaiRepository khuyenMaiRepository;
    @Override
    public Page<KhuyenMai> getListKhuyenMai(int page, int size, String keyword) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        if (Strings.isBlank(keyword)){
           return  khuyenMaiRepository.findAll(pageRequest);
        }
        return khuyenMaiRepository.findAllByTenKhuyenMaiLike(keyword);
    }
}
