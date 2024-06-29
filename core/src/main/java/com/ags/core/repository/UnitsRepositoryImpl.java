package com.ags.core.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ags.core.model.Units;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UnitsRepositoryImpl implements UnitsRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(Units units) {
		return jdbcTemplate.update("INSERT INTO units (unit_id, unit) VALUES(?,?)",
				new Object[] { units.getUnitId(), units.getUnit() });
	}

	@Override
	public int update(Units units) {
		return jdbcTemplate.update("UPDATE units SET unit=? WHERE unit_id=?",
				new Object[] { units.getUnit(), units.getUnitId() });
	}

	@Override
	public Units findById(int unitId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM units WHERE unit_id=?",
					BeanPropertyRowMapper.newInstance(Units.class), unitId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deleteById(int unitId) {
		return jdbcTemplate.update("DELETE FROM units WHERE unit_id=?", unitId);
	}

	@Override
	public List<Units> findAll() {
		return jdbcTemplate.query("SELECT * from units", BeanPropertyRowMapper.newInstance(Units.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM units");
	}

	@Override
	public Units findByUnit(String unitName) {
		return jdbcTemplate.query(String.format("SELECT unit_id from units WHERE unit='%s'", unitName),
				BeanPropertyRowMapper.newInstance(Units.class)).stream().findFirst().orElseThrow();
	}

}
