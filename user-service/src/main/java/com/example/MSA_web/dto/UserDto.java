package com.example.MSA_web.dto;

import java.util.Date;
import java.util.List;

import com.example.MSA_web.vo.ResponseOrder;

import lombok.Data;

@Data
public class UserDto {
	//VO에서 받아올 데이터
	private String email;
	private String name;
	private String pwd;
	
	//현재(DTo)에서 생성할 데이터
	private String userId;
	private Date createAt;
	private String encryptedPwd;
	
	//사용자가 주문한 내역을 함께 반환
	private List<ResponseOrder> orders;
}
