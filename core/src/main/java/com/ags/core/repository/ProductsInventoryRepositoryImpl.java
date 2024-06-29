package com.ags.core.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.ProductsInventory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductsInventoryRepositoryImpl implements ProductsInventoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(ProductsInventory productsInventory) {
		return jdbcTemplate.update(
				"INSERT INTO products_inventory (pi_id, product_id, vendor_id, qty, unit_id) VALUES(?,?,?,?,?)",
				new Object[] { productsInventory.getPiId(), productsInventory.getProductId(),
						productsInventory.getVendorId(), productsInventory.getQty(), productsInventory.getUnitId() });
	}

	@Override
	public int update(ProductsInventory productsInventory) {
		return jdbcTemplate
				.update("UPDATE products_inventory SET product_id=?, vendor_id=?, qty=?, unit_id=? WHERE pi_id=?",
						new Object[] { productsInventory.getProductId(), productsInventory.getVendorId(),
								productsInventory.getQty(), productsInventory.getUnitId(),
								productsInventory.getPiId() });
	}

	@Override
	public ProductsInventory findById(int piId) {
		try {
			ProductsInventory productsInventory = jdbcTemplate.queryForObject(
					"SELECT pi.pi_id, pi.product_id, p.product_name, pi.vendor_id, v.vendor_name, pi.qty, pi.unit_id, u.unit, pi.modified_date "
							+ "FROM products_inventory pi, products p, vendors v, units u "
							+ "WHERE pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id AND pi.pi_id = ?",
					BeanPropertyRowMapper.newInstance(ProductsInventory.class), piId);
			return productsInventory;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteById(int piId) {
		return jdbcTemplate.update("DELETE FROM products_inventory WHERE pi_id=?", piId);
	}

	@Override
	public List<ProductsInventory> findAll() {
		try {
			return jdbcTemplate.query(
					"SELECT pi.pi_id, pi.product_id, p.product_name, pi.vendor_id, v.vendor_name, pi.qty, pi.unit_id, u.unit, pi.modified_date "
							+ "FROM products_inventory pi, products p, vendors v, units u "
							+ "WHERE pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id",
					BeanPropertyRowMapper.newInstance(ProductsInventory.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM products_inventory");
	}

	@Override
	public ProductsInventory findByProductVendorUnit(int productId, int vendorId, int unitId) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM products_inventory WHERE product_id = ? AND vendor_id = ? AND unit_id = ?",
					BeanPropertyRowMapper.newInstance(ProductsInventory.class), productId, vendorId, unitId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
