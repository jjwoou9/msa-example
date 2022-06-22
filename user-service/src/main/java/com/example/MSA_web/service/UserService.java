package com.example.MSA_web.service;

import com.example.MSA_web.dto.UserDto;
import com.example.MSA_web.jpa.UserEntity;

public interface UserService {
	//회원가입 처리
	UserDto createUser(UserDto userDto);
	
	//회원 정보 조회
	UserDto getUserByUserId(String userId);
	
	//전체 회원 조회
	Iterable<UserEntity> getUserByAll();
}
