package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatLieuDTO {
	private Long chat_lieu_id;
	
	private String ten_chat_lieu;
	
	private String nguoi_tao;
	
	private Date ngay_tao;
	
	private String nguoi_chinh_sua;
	
	private Date ngay_chinh_sua;
}
