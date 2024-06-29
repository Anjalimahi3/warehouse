package com.ags.core.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.PurchaseHistory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseHistoryRepositoryImpl implements PurchaseHistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(PurchaseHistory purchaseHistory) {
		return jdbcTemplate.update(
				"INSERT INTO purchase_history (ph_id, pi_id, qty, price_per_unit, total_price) VALUES(?,?,?,?,?)",
				new Object[] { purchaseHistory.getPhId(), purchaseHistory.getPiId(), purchaseHistory.getQty(),
						purchaseHistory.getPricePerUnit(), purchaseHistory.getTotalPrice() });
	}

	@Override
	public int update(PurchaseHistory purchaseHistory) {
		return jdbcTemplate
				.update("UPDATE purchase_history SET pi_id=?, qty=?, price_per_unit=?, total_price=? WHERE ph_id=?",
						new Object[] { purchaseHistory.getPiId(), purchaseHistory.getQty(),
								purchaseHistory.getPricePerUnit(), purchaseHistory.getTotalPrice(),
								purchaseHistory.getPhId() });
	}

	@Override
	public PurchaseHistory findById(int phId) {
		try {
			PurchaseHistory purchaseHistory = jdbcTemplate.queryForObject(
					"SELECT ph.ph_id, pi.pi_id, p.product_name, v.vendor_name, u.unit, ph.qty, ph.price_per_unit, ph.total_price, ph.purchase_date_time "
							+ "FROM purchase_history ph, products_inventory pi, products p, vendors v, units u "
							+ "WHERE ph.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id "
							+ "AND ph.ph_id = ?",
					BeanPropertyRowMapper.newInstance(PurchaseHistory.class), phId);
			return purchaseHistory;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteById(int phId) {
		return jdbcTemplate.update("DELETE FROM purchase_history WHERE ph_id=?", phId);
	}

	@Override
	public List<PurchaseHistory> findAll() {
		try {
			return jdbcTemplate.query(
					"SELECT ph.ph_id, pi.pi_id, p.product_name, v.vendor_name, u.unit, ph.qty, ph.price_per_unit, ph.total_price, ph.purchase_date_time "
							+ "FROM purchase_history ph, products_inventory pi, products p, vendors v, units u "
							+ "WHERE ph.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id ",
					BeanPropertyRowMapper.newInstance(PurchaseHistory.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM purchase_history");
	}

	@Override
	public Timestamp getMinimumTime() {
		return jdbcTemplate.queryForObject("SELECT MIN(purchase_date_time) FROM purchase_history", Timestamp.class);
	}

	@Override
	public List<PurchaseHistory> findTotalByDate(final String date) {
		try {
			return jdbcTemplate.query(
					"SELECT pi_id, SUM(qty) as qty, SUM(total_price) as total_price "
							+ "FROM purchase_history WHERE CAST(purchase_date_time as DATE) = ? GROUP BY pi_id;",
					BeanPropertyRowMapper.newInstance(PurchaseHistory.class), date);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Timestamp getMaximumTime() {
		return jdbcTemplate.queryForObject("SELECT MAX(purchase_date_time) FROM purchase_history", Timestamp.class);
	}

}
