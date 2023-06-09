package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ly_do_tra_hang")

public class LyDoTraHang extends BaseEntity implements Serializable {
    

    @Column(name = "ly_do", columnDefinition = "text")
    private String lyDo;

    @OneToMany(mappedBy = "lyDoTraHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TraHang> traHang;

    @ManyToOne
    @JoinColumn(name = "hinh_anh_id", insertable = false, updatable = false)
    private HinhAnh hinhAnhTraHang;
}
