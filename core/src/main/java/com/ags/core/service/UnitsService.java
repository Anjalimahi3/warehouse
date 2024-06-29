package com.ags.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.dto.Response;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.Units;
import com.ags.core.repository.UnitsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnitsService {

	private final UnitsRepository unitsRepository;

	public List<Units> getAllUnits() {
		final List<Units> units = new ArrayList<>();
		try {
			unitsRepository.findAll().forEach(units::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return units;
	}

	public Units getUnitsById(final int unitId) {
		Units units = null;
		try {
			units = unitsRepository.findById(unitId);
			if (Objects.isNull(units)) {
				throw new NoSuchElementExistsException("Unit not found with id = " + unitId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return units;
	}

	public Response createUnits(final Units units) {
		Units updatedUnits = null;
		try {
			unitsRepository.save(new Units(units.getUnitId(), units.getUnit()));
			updatedUnits = unitsRepository.findByUnit(units.getUnit());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new Response("Unit created successfully with id = " + updatedUnits.getUnitId());
	}

	public Response updateUnits(final int unitId, final Units units) {
		try {
			final Units unitsFromDB = unitsRepository.findById(unitId);
			if (Objects.isNull(unitsFromDB)) {
				throw new NoSuchElementExistsException("Unit not found with id = " + unitId);
			} else {
				unitsFromDB.setUnit(units.getUnit());
				unitsRepository.update(unitsFromDB);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Units updated successfully");
	}

	public Response deleteUnits(final int unitId) {
		try {
			int result = unitsRepository.deleteById(unitId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find Units with id = " + unitId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Units was deleted successfully.");
	}

	public Units getOrCreateUnits(final String unitName) {
		Units unitsFromDb = findByUnitName(unitName);
		if (Objects.isNull(unitsFromDb)) {
			unitsFromDb = createUnits(0, unitName);
		}
		return unitsFromDb;
	}

	private Units findByUnitName(String unitName) {
		Units units = null;
		try {
			units = unitsRepository.findByUnit(unitName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return units;
	}

	private Units createUnits(final int unitId, final String unitName) {
		unitsRepository.save(new Units(unitId, unitName));
		return findByUnitName(unitName);
	}

}
