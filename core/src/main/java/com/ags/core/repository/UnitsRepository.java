package com.ags.core.repository;

import java.util.List;

import com.ags.core.model.Units;

public interface UnitsRepository {

	int save(final Units units);

	int update(final Units units);

	Units findById(int id);

	int deleteById(int id);

	List<Units> findAll();

	int deleteAll();

	Units findByUnit(String unit);

}
