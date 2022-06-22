package com.example.MSA_web.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long>{
	CatalogEntity findByProductId(String productId);
}
