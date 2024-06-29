package com.ags.core.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.ProductSummary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSummaryRepositoryImpl implements ProductSummaryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(ProductSummary productSummary) {
		return jdbcTemplate.update(
				"INSERT INTO product_summary (ps_id, pi_id, date, opening_stock, closing_stock, total_purchase_rate, total_sale_rate) VALUES(?,?,?,?,?,?,?)",
				new Object[] { productSummary.getPsId(), productSummary.getPiId(), productSummary.getDate(),
						productSummary.getOpeningStock(), productSummary.getClosingStock(),
						productSummary.getTotalPurchaseRate(), productSummary.getTotalSaleRate() });
	}

	@Override
	public int update(ProductSummary productSummary) {
		return jdbcTemplate.update(
				"UPDATE product_summary SET pi_id=?, date=?, opening_stock=?, closing_stock=?, total_purchase_rate=?, total_sale_rate=? WHERE ps_id=?",
				new Object[] { productSummary.getPiId(), productSummary.getDate(), productSummary.getOpeningStock(),
						productSummary.getClosingStock(), productSummary.getTotalPurchaseRate(),
						productSummary.getTotalSaleRate(), productSummary.getPsId() });
	}

	@Override
	public ProductSummary findById(int psId) {
		try {
			ProductSummary productSummary = jdbcTemplate.queryForObject("SELECT * FROM product_summary WHERE ps_id = ?",
					BeanPropertyRowMapper.newInstance(ProductSummary.class), psId);
			return productSummary;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteById(int psId) {
		return jdbcTemplate.update("DELETE FROM product_summary WHERE pi_id=?", psId);
	}

	@Override
	public List<ProductSummary> findAll() {
		try {
			return jdbcTemplate.query("SELECT * FROM product_summary",
					BeanPropertyRowMapper.newInstance(ProductSummary.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM product_summary");
	}

	@Override
	public Date getMaxDate() {
		return jdbcTemplate.queryForObject("SELECT MAX(date) FROM product_summary", Date.class);
	}

}
