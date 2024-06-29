package com.ags.core.repository;

import java.util.List;

import com.ags.core.model.Products;

public interface ProductsRepository {

	int save(final Products products);

	int update(final Products products);

	Products findById(int id);

	int deleteById(int id);

	List<Products> findAll();

	int deleteAll();

	Products findByProductName(String productName);
}
