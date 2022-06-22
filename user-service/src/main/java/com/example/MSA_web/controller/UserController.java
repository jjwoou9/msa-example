package com.example.MSA_web.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSA_web.dto.UserDto;
import com.example.MSA_web.jpa.UserEntity;
import com.example.MSA_web.service.UserService;
import com.example.MSA_web.vo.Greeting;
import com.example.MSA_web.vo.RequestUser;
import com.example.MSA_web.vo.ResponseUser;

@RestController
@RequestMapping("/user-service") // apigateway 라우팅을 위한
public class UserController {

	Environment env;
	private Greeting greeting;
	private UserService userService;

	@Autowired
	public UserController(Environment env, UserService userService, Greeting greeting) {// 매개변수의 값을 현재 객체에 저장
		this.env = env;
		this.userService = userService;
		this.greeting = greeting;
	}

	@PostMapping("/users")
	public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
		// RequestUser -> UserDto
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = mapper.map(user, UserDto.class);

		// createUser 서비스를 호출
		userService.createUser(userDto);

		// UserDto -> ResponseUser
		ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

		// 201 created 상태코드를 반환하도록 수정
		return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
	}

	@GetMapping("/welcome")
	public String welcome() {
		return greeting.getMessage();// application.yml의 greeting.message의 값을 리턴
	}

	@GetMapping("/health_check")
	public String healthCheck() {
		// 할당받은 포트번호 출력
		return String.format("OK ... Port num = %s", env.getProperty("local.server.port"));
	}

	@GetMapping("/users/{userId}") // 해당 회원 정보 조회
	public ResponseEntity<ResponseUser> getUsers(@PathVariable("userId") String userId) {
		// 회원 정보 조회
		UserDto userDto = userService.getUserByUserId(userId);
		
		// UserDto -> ResponseUser
		ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseUser);
	}

	@GetMapping("/users") // 전체 회원 정보조회
	public ResponseEntity<List<ResponseUser>> getUsers() {
		// 회원 정보 조회
		Iterable<UserEntity> userList = userService.getUserByAll();
		
		// 조회 결과 리스트의 UserEntity -> ResponseUser
		List<ResponseUser> resultList = new ArrayList();
		// Iterable의 요소 하나씩 ResponseUser로 변환, List에 add
		userList.forEach(v->{
			resultList.add(new ModelMapper().map(v, ResponseUser.class));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}
