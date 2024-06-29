package com.ags.core.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.SaleHistory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SaleHistoryRepositoryImpl implements SaleHistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(SaleHistory saleHistory) {
		return jdbcTemplate.update(
				"INSERT INTO sale_history (sh_id, pi_id, qty, price_per_unit, total_price) VALUES(?,?,?,?,?)",
				new Object[] { saleHistory.getShId(), saleHistory.getPiId(), saleHistory.getQty(),
						saleHistory.getPricePerUnit(), saleHistory.getTotalPrice() });
	}

	@Override
	public int update(SaleHistory saleHistory) {
		return jdbcTemplate.update(
				"UPDATE sale_history SET pi_id=?, qty=?, price_per_unit=?, total_price=? WHERE sh_id=?",
				new Object[] { saleHistory.getPiId(), saleHistory.getQty(), saleHistory.getPricePerUnit(),
						saleHistory.getTotalPrice(), saleHistory.getShId() });
	}

	@Override
	public SaleHistory findById(int shId) {
		try {
			SaleHistory saleHistory = jdbcTemplate.queryForObject(
					"SELECT sh.sh_id, pi.pi_id, p.product_name, v.vendor_name, u.unit, sh.qty, sh.price_per_unit, sh.total_price, sh.sale_date_time "
							+ "FROM sale_history sh, products_inventory pi, products p, vendors v, units u "
							+ "WHERE sh.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id "
							+ "AND sh_id = ?",
					BeanPropertyRowMapper.newInstance(SaleHistory.class), shId);
			return saleHistory;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteById(int shId) {
		return jdbcTemplate.update("DELETE FROM sale_history WHERE sh_id=?", shId);
	}

	@Override
	public List<SaleHistory> findAll() {
		try {
			return jdbcTemplate.query(
					"SELECT sh.sh_id, pi.pi_id, p.product_name, v.vendor_name, u.unit, sh.qty, sh.price_per_unit, sh.total_price, sh.sale_date_time "
							+ "FROM sale_history sh, products_inventory pi, products p, vendors v, units u "
							+ "WHERE sh.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id",
					BeanPropertyRowMapper.newInstance(SaleHistory.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM sale_history");
	}

	@Override
	public Timestamp getMinimumTime() {
		return jdbcTemplate.queryForObject("SELECT MIN(sale_date_time) FROM sale_history", Timestamp.class);
	}

}
