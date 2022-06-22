package com.example.MSA_web.service;

import com.example.MSA_web.jpa.CatalogEntity;

public interface CatalogService {
	Iterable<CatalogEntity> getAllCatalogs();
}
