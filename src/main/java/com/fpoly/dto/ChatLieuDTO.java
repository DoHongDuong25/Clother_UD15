package com.fpoly.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatLieuDTO extends BaseDTO{
	private Long id;
	
	private String tenChatLieu;
	
	private Boolean daXoa;
}
