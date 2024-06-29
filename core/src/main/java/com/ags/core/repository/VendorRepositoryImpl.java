package com.ags.core.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.Vendors;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VendorRepositoryImpl implements VendorsRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(Vendors vendors) {
		return jdbcTemplate.update("INSERT INTO vendors (vendor_id, vendor_name) VALUES(?,?)",
				new Object[] { vendors.getVendorId(), vendors.getVendorName() });
	}

	@Override
	public int update(Vendors vendors) {
		return jdbcTemplate.update("UPDATE vendors SET vendor_name=? WHERE vendor_id=?",
				new Object[] { vendors.getVendorName(), vendors.getVendorId() });
	}

	@Override
	public Vendors findById(int vendorId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM vendors WHERE vendor_id=?",
					BeanPropertyRowMapper.newInstance(Vendors.class), vendorId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deleteById(int vendorId) {
		return jdbcTemplate.update("DELETE FROM vendors WHERE vendor_id=?", vendorId);
	}

	@Override
	public List<Vendors> findAll() {
		return jdbcTemplate.query("SELECT * from vendors", BeanPropertyRowMapper.newInstance(Vendors.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM vendors");
	}

	@Override
	public Vendors findByVendorName(String vendorName) {
		return jdbcTemplate.query(String.format("SELECT vendor_id from vendors WHERE vendor_name='%s'", vendorName),
				BeanPropertyRowMapper.newInstance(Vendors.class)).stream().findFirst().orElseThrow();
	}

}
