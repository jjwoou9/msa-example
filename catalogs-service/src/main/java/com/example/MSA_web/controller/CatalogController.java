package com.example.MSA_web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.env.Environment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSA_web.jpa.CatalogEntity;
import com.example.MSA_web.service.CatalogService;
import com.example.MSA_web.vo.ResponseCatalog;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
	Environment env;
	CatalogService catalogService;

	@Autowired
	public CatalogController(Environment env, CatalogService catalogService) {
		this.env = env;
		this.catalogService = catalogService;
	}

	@GetMapping("/health_check")
	public String healthCheck() {
		// 할당받은 포트번호 출력
		return String.format("OK ... Port num = %s", env.getProperty("local.server.port"));
	}

	@GetMapping("/catalogs")
	public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
		Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

		// CatalogEntity ->ResponseCatalog
		List<ResponseCatalog> resultList = new ArrayList<>();
		catalogList.forEach(v -> {
			resultList.add(new ModelMapper().map(v, ResponseCatalog.class));
		});
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
}
