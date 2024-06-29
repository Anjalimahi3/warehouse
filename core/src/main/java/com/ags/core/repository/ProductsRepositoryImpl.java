package com.ags.core.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.Products;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductsRepositoryImpl implements ProductsRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(Products products) {
		return jdbcTemplate.update("INSERT INTO products (product_id, product_name) VALUES(?,?)",
				new Object[] { products.getProductId(), products.getProductName() });
	}

	@Override
	public int update(Products products) {
		return jdbcTemplate.update("UPDATE products SET product_name=? WHERE product_id=?",
				new Object[] { products.getProductName(), products.getProductId() });
	}

	@Override
	public Products findById(int productId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id=?",
					BeanPropertyRowMapper.newInstance(Products.class), productId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deleteById(int productId) {
		return jdbcTemplate.update("DELETE FROM products WHERE product_id=?", productId);
	}

	@Override
	public List<Products> findAll() {
		return jdbcTemplate.query("SELECT * from products", BeanPropertyRowMapper.newInstance(Products.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM products");
	}

	@Override
	public Products findByProductName(String productName) {
		return jdbcTemplate.query(String.format("SELECT product_id from products WHERE product_name='%s'", productName),
				BeanPropertyRowMapper.newInstance(Products.class)).stream().findFirst().orElseThrow();
	}

}
