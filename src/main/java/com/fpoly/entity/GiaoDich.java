package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giao_dich")
@EntityListeners(AuditingEntityListener.class)
public class GiaoDich extends BaseEntity implements Serializable {
    

    @OneToMany(mappedBy="giaoDich")
    private Set<HoaDon> hoaDon;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;


    @Column(name = "trang_thai", columnDefinition = "int default(0) not null")
    private int trangThai;
}
