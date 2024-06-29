package com.ags.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ags.core.dto.Response;
import com.ags.core.model.Units;
import com.ags.core.service.UnitsService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/units")
public class UnitsController {

	private final UnitsService unitsService;

	@GetMapping
	public List<Units> getAllUnits() {
		return unitsService.getAllUnits();
	}

	@GetMapping("/{unitId}")
	public Units getUnitsById(@PathVariable("unitId") final int unitId) {
		return unitsService.getUnitsById(unitId);
	}

	@PostMapping
	public Response createUnits(@RequestBody final Units units) {
		return unitsService.createUnits(units);
	}

	@PutMapping("/{unitId}")
	public Response updateUnits(@PathVariable("unitId") final int unitId,
			@RequestBody final Units units) {
		return unitsService.updateUnits(unitId, units);
	}

	@DeleteMapping("/{unitId}")
	public Response deleteUnitsById(@PathVariable("unitId") final int unitId) {
		return unitsService.deleteUnits(unitId);
	}

}
